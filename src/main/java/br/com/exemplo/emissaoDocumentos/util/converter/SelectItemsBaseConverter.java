package br.com.exemplo.emissaoDocumentos.util.converter;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

public abstract class SelectItemsBaseConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {        
        return findValueByStringConversion(context, component, value, this);    
    }
    
    public static Object findValueByStringConversion(FacesContext context, UIComponent component, String value, Converter converter) {
        return findValueByStringConversion(context, component, new SelectItemsIterator(context, component), value, converter);        
    }
 
    private static Object findValueByStringConversion(FacesContext context, UIComponent component, Iterator<SelectItem> items, String value, Converter converter) {
        while (items.hasNext()) {
            SelectItem item = items.next();
            if (item instanceof SelectItemGroup) {
                SelectItem subitems[] = ((SelectItemGroup) item).getSelectItems();
                if (!isEmpty(subitems)) {
                    Object object = findValueByStringConversion(context, component, new ArrayIterator(subitems), value, converter);
                    if (object != null) {
                        return object;
                    }
                }
            } else if (!item.isNoSelectionOption() && value.equals(converter.getAsString(context, component, item.getValue()))) {
                return item.getValue();
            }
        }        
        return null;
    }
 
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;    
    }
 
    static class ArrayIterator implements Iterator<SelectItem> {
 
        public ArrayIterator(SelectItem items[]) {
            this.items = items;
        }
 
        private SelectItem items[];
        private int index = 0;
 
        public boolean hasNext() {
            return (index < items.length);
        }
 
        public SelectItem next() {
            try {
                return (items[index++]);
            }
            catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
 
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
