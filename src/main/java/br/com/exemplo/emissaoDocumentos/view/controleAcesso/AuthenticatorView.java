package br.com.exemplo.emissaoDocumentos.view.controleAcesso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import br.com.exemplo.emissaoDocumentos.exception.RecursoNaoEncontradoException;
import br.com.exemplo.emissaoDocumentos.futronic.FutronicConstantes;
import br.com.exemplo.emissaoDocumentos.futronic.FutronicHelper;
import br.com.exemplo.emissaoDocumentos.futronic.config.LFDParameters;
import br.com.exemplo.emissaoDocumentos.model.Perfil;
import br.com.exemplo.emissaoDocumentos.model.Usuario;
import br.com.exemplo.emissaoDocumentos.service.UsuarioService;
import br.com.exemplo.emissaoDocumentos.util.LoggerUtils;
import br.com.exemplo.emissaoDocumentos.view.AbstractView;

@Controller
@Scope("request")
public class AuthenticatorView extends AbstractView implements
        AuthenticationProvider {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioService usuarioService;

    private Usuario usuario;
    private String chave;
    private byte[] loginBiometria;
    private static FutronicHelper fh;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public void loginByBiometria() {
        capturaDigitalLogin();
        login();
    }

    public String login() {
        try {
            List<Usuario> listaUsuario = new ArrayList<Usuario>();
            if (validarNumeroJCE()) {
                Usuario user = usuarioService.getUsuarioPorNumeroJCE(usuario
                        .getNumeroJCE());
                // if (user != null
                // && usuarioService.loginSpringSecurity(user,
                // loginBiometria, fh)) {
                if (user != null) {
                    confirmarAutenticacao(user);
                    redirecionar("documento/lista_documento.xhtml");
                } else {
                    negarAutenticacao();
                }
            } else if (validarNome()) {
                listaUsuario = usuarioService.listaUsuarioPorNome(usuario);
                for (Usuario usu : listaUsuario) {
                    //					if (usuarioService.loginSpringSecurity(usu, loginBiometria,
                    //							fh)) {
                    if (usu != null) {
                        confirmarAutenticacao(usu);
                        redirecionar("documento/lista_documento.xhtml");
                        break;
                    }
                }
                if (getSession().getUsuario() == null) {
                    negarAutenticacao();
                }
            } else {
                negarAutenticacao();
            }
        } catch (RecursoNaoEncontradoException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean validarNumeroJCE() {
        return usuario.getNumeroJCE() != null
                && usuario.getNumeroJCE().length() == 11;
    }

    private boolean validarNome() {
        return !usuario.getPrimeiroNome().equals("")
                && !usuario.getSegundoNome().equals("");
    }

    /*
     * private void loginSpringSecurity(Usuario user) { boolean autenticado =
     * false;
     *
     * if (loginBiometria != null && user.getDigital() != null) { byte[] dig1 =
     * fh.geraTemplate(user.getDigital()); byte[] dig2 =
     * fh.geraTemplate(loginBiometria); autenticado = fh.comparar(dig1, dig2); }
     * else { LoggerUtils.info("Acesso por password!"); if (user.getSenha() !=
     * null && password != null) { autenticado =
     * user.getSenha().equals(password); } } if (autenticado) {
     * confirmarAutenticacao(user); } else { negarAutenticacao(); } }
     */
    private void confirmarAutenticacao(Usuario user) {
        Collection<Perfil> listaPerfil = new ArrayList<Perfil>();
        Perfil perfil = new Perfil();
        perfil.setNome("ADMINISTRADOR");
        // listaPerfil.add(user.getPerfil());
        listaPerfil.add(perfil);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, listaPerfil);
        token.setDetails(user);

        SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.getContext().setAuthentication(token);
        getSession().setUsuario(user);
        // try {
        // FacesContext.getCurrentInstance().getExternalContext()
        // .redirect("pages/index.xhtml");
        // } catch (IllegalArgumentException | IOException ex) {
        // message(ex.getMessage());
        // }
    }

    private void negarAutenticacao() {
        logout();
        FacesContext.getCurrentInstance().addMessage(
                getMessage("msg.erro.loginInvalido"),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        getMessage("msg.erro.loginInvalido"), ""));
    }

    public void capturaDigitalLogin() {
        fh = FutronicHelper.getInstance();
        fh.setDedoVivo(true);
        fh.abrir();
        fh.getSensor();
        fh.getDeviceInfo();
        fh.getVersion();
        fh.getSerial();
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_LFD);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_DIODES);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_GET_IMAGE2);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_IS_CALIBRATED);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_IS_LFD_CALIBRATED);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_LONG_IMAGE);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_ROLL);
        fh.isFeaturePresent(FutronicConstantes.FTR_SCANNER_FEATURE_SERIAL_NUMBER);
        LFDParameters lfd = new LFDParameters();
        fh.getLibScan().ftrScanGetLFDParameters(lfd);
        fh.getLibScan().ftrScanGetProperty(fh.getSensor(), 1, null);
        long[] f = new long[20];
        fh.getOptions(f);
        fh.setDedoVivo(true);
        fh.isDedoNoFinger();
        byte[] imagem1 = fh.getImagem();
        if (imagem1 != null) {
            loginBiometria = imagem1;
        } else {
            LoggerUtils.info("Sem dedo");
        }
    }

    public void logout() {
        SecurityContextHolder.clearContext();
        getSession().setUsuario(null);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        redirecionar("login.xhtml");
    }

    private void message(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message));
    }

    public byte[] getLoginBiometria() {
        return loginBiometria;
    }

    public void setLoginBiometria(byte[] loginBiometria) {
        this.loginBiometria = loginBiometria;
    }

    @Override
    public Authentication authenticate(Authentication arg0)
            throws AuthenticationException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}
