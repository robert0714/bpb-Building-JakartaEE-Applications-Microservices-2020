/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package com.bpbonline.jsfhelloworld.datamodel;

import com.bpbonline.jsfhelloworld.entity.Profiles;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author avbravo
 */
public class ProfilesDataModel extends ListDataModel<Profiles> implements SelectableDataModel<Profiles>{

    public ProfilesDataModel() {
    }
    public ProfilesDataModel(List<Profiles>data) {
        super(data);
    }

    @Override
    public Profiles  getRowData(String rowKey) {
        List<Profiles> roleList = (List<Profiles>) getWrappedData();
        for (Profiles role : roleList) {
             if (role.getIdprofile().equals(rowKey)) {
                 return role;
             }
        }
        return null;
     }
     @Override
     public Object getRowKey(Profiles role) {
         return role.getIdprofile();
     }


}
