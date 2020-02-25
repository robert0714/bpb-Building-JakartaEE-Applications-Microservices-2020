/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bpbonline.jsfhelloworld.datamodel;

import com.bpbonline.jsfhelloworld.entity.User;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class UsuarioDataModel extends ListDataModel<User> implements SelectableDataModel<User>{

    public UsuarioDataModel() {
    }
    public UsuarioDataModel(List<User>data) {
        super(data);
    }

    @Override
    public User  getRowData(String rowKey) {
        List<User> usuarioList = (List<User>) getWrappedData();
        for (User usuario : usuarioList) {
             if (usuario.getUsername().equals(rowKey)) {
                 return usuario;
             }
        }
        return null;
     }
     @Override
     public Object getRowKey(User usuario) {
         return usuario.getUsername();
     }


}
