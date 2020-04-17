/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bpbonline.jsfcrud.datamodel;

import com.bpbonline.jsfcrud.entity.Profile;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class ProfilesDataModel extends ListDataModel<Profile> implements SelectableDataModel<Profile>{

    public ProfilesDataModel() {
    }
    public ProfilesDataModel(List<Profile>data) {
        super(data);
    }

    @Override
    public Profile  getRowData(String rowKey) {
        List<Profile> roleList = (List<Profile>) getWrappedData();
        for (Profile role : roleList) {
             if (role.getIdprofile().equals(rowKey)) {
                 return role;
             }
        }
        return null;
     }
     @Override
     public Object getRowKey(Profile role) {
         return role.getIdprofile();
     }


}
