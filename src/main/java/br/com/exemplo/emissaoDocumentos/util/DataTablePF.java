package br.com.exemplo.emissaoDocumentos.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
*
*/
public class DataTablePF<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<T>	listItem;
	private List<T>	mutiplySelectedList;
	private T			selectedItem;

	public DataTablePF(List<T> list){
		this.listItem = list;
	}

	public DataTablePF(){
		this.listItem = new ArrayList<>();
	}

	public List<T> getListItem(){
		return listItem;
	}

	public void setListItem(List<T> listItem){
		this.listItem = listItem;
	}

	public T getSelectedItem(){
		return selectedItem;
	}

	public List<T> getMutiplySelectedList(){
		return mutiplySelectedList;
	}

	public void setMutiplySelectedList(
			List<T> mutiplySelectedList){
		this.mutiplySelectedList = mutiplySelectedList;
	}

	public void setSelectedItem(T selectedItem){
		this.selectedItem = selectedItem;
	}

	public void clean(){
		if (this.getListItem() != null){
			this.getListItem().clear();
		}
		if (this.getSelectedItem() != null){
			this.setSelectedItem(null);
		}
	}

	public boolean renderButtons(){
		if (this.listItem != null){
			return this.listItem.isEmpty();
		}
		return false;
	}

	public int qtdElementsTable(){
		if (this.listItem != null){
			return this.listItem.size();
		}
		return 0;
	}
}
