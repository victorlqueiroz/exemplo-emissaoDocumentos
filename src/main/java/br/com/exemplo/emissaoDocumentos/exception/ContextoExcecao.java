/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exemplo.emissaoDocumentos.exception;

import java.util.HashMap;

/**
 *
 */
public class ContextoExcecao extends HashMap<Object, Object> {

    public <T> T get(Object key, T defaultValue) {
        Object value = get(key);
        if (value == null) {
            value = defaultValue;
            set(key, value);
        }

        return (T) value;
    }

    public void set(Object key, Object value) {
        put(key, value);
    }
}
