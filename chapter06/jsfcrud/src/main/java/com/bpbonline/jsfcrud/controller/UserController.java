/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfcrud.controller;

// <editor-fold defaultstate="collapsed" desc="imports">
import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordb.configuration.JmoordbControllerEnvironment;
import com.avbravo.jmoordb.interfaces.IController;
import com.avbravo.jmoordb.mongodb.history.services.AutoincrementableServices;
import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordbutils.DateUtil;
import com.avbravo.jmoordbutils.JsfUtil;
import com.avbravo.jmoordbutils.printer.Printer;
import com.avbravo.jmoordbutils.JmoordbResourcesFiles;
import com.avbravo.jmoordbutils.ReportUtils;
import com.bpbonline.jsfcrud.datamodel.UsuarioDataModel;
import com.bpbonline.jsfcrud.entity.Profile;
import com.bpbonline.jsfcrud.entity.User;
import com.bpbonline.jsfcrud.repository.ProfileRepository;
import com.bpbonline.jsfcrud.repository.UserRepository;
import com.bpbonline.jsfcrud.services.ProfileServices;
import com.bpbonline.jsfcrud.services.UserServices;


import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.primefaces.event.SelectEvent;
// </editor-fold>

/**
 *
 * @authoravbravo
 */
@Named
@ViewScoped
@Getter
@Setter
public class UserController implements Serializable, IController {

// <editor-fold defaultstate="collapsed" desc="fields">  
    private static final long serialVersionUID = 1L;

    private Boolean writable = false;
    private String passwordnewrepeat;
    //DataModel
    private UsuarioDataModel userDataModel;

    Integer page = 1;
    Integer rowPage = 25;
    List<Integer> pages = new ArrayList<>();

    //Entity
    User user = new User();
    User userSelected;
    User userSearch = new User();

    //List
    List<User> userList = new ArrayList<>();
    List<User> userListSelected = new ArrayList<>();
    //
    List<Profile> profilesList = new ArrayList();

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="repository">
    //Repository
    @Inject
    ProfileRepository profileRepository;
    @Inject
    UserRepository userRepository;

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="services">
    //Services
    @Inject
    AutoincrementableServices autoincrementableServices;
    @Inject
    ErrorInfoServices errorServices;
    @Inject
    ProfileServices profilesServicesr;
   
    @Inject
    UserServices userServices;
    @Inject
    JmoordbResourcesFiles rf;
    @Inject
    Printer printer;

    //List of Relations
    //Repository of Relations
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="getter/setter">
    public List<Integer> getPages() {

        return userRepository.listOfPage(rowPage);
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="constructor">
    public UserController() {
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {

            /*
            configurar el ambiente del contuserler
             */
            HashMap parameters = new HashMap();
            User jmoordb_user = (User) JmoordbContext.get("jmoordb_user");
            //    parameters.put("P_EMPRESA", jmoordb_user.getEmpresa().getDescripcion());

            JmoordbControllerEnvironment jmc = new JmoordbControllerEnvironment.Builder()
                    .withController(this)
                    .withRepository(userRepository)
                    .withEntity(user)
                    .withService(userServices)
                    .withNameFieldOfPage("page")
                    .withNameFieldOfRowPage("rowPage")
                    .withTypeKey("primary")
                    .withSearchLowerCase(true)
                    .withPathReportDetail("/resources/reportes/user/details.jasper")
                    .withPathReportAll("/resources/reportes/user/all.jasper")
                    .withparameters(parameters)
                    .withResetInSave(true)
                    .withAction("golist")
                    .build();

            start();
            //En este caso desencriptamos el password
            String action = getAction();
            if (action.equals("view")) {
                user.setPassword(JsfUtil.desencriptar(user.getPassword()));
                profilesList = user.getProfile();
                userSelected = user;
            }

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="handleSelect">
    public void handleSelect(SelectEvent event) {
        try {
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="move(Integer page)">
    @Override
    public void move(Integer page) {
        try {
            this.page = page;
            userDataModel = new UsuarioDataModel(userList);
            Document doc;

            switch (getSearch()) {
                case "_init":
                case "_autocomplete":
                    userList = userRepository.findPagination(page, rowPage);
                    break;

                case "username":
                    if (getValueSearch() != null) {
                        userSearch.setUsername(getValueSearch().toString());
                        doc = new Document("username", userSearch.getUsername());
                        userList = userRepository.findPagination(doc, page, rowPage, new Document("username", -1));
                    } else {
                        userList = userRepository.findPagination(page, rowPage);
                    }

                    break;
                case "activo":
                    if (getValueSearch() != null) {
                        userSearch.setActive(getValueSearch().toString());
                        doc = new Document("activo", userSearch.getActive());
                        userList = userRepository.findPagination(doc, page, rowPage, new Document("username", -1));
                    } else {
                        userList = userRepository.findPagination(page, rowPage);
                    }
                    break;

                default:
                    userList = userRepository.findPagination(page, rowPage);
                    break;
            }

            userDataModel = new UsuarioDataModel(userList);

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);

        }

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean beforeSave()">
    public Boolean beforeSave() {
        try {
            //password nuevo no coincide
            if (!user.getPassword().equals(passwordnewrepeat)) {
                JsfUtil.warningMessage(rf.getMessage("warning.passwordnocoinciden"));
                return false;
            }

            user.setProfile(profilesList);
            user.setPassword(JsfUtil.encriptar(user.getPassword()));
            return true;
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean beforeEdit()">
    public Boolean beforeEdit() {
        try {
            user.setProfile(profilesList);
            user.setPassword(JsfUtil.encriptar(user.getPassword()));
            return true;
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean beforeDelete()">
    @Override
    public Boolean beforeDelete() {
        Boolean delete = userServices.isDeleted(user);
        if (!delete) {
            JsfUtil.warningDialog(rf.getMessage("warning.advertencia"), rf.getMessage("warning.nosepuedeeliminar"));
        }
        return delete;
    }

    // </editor-fold>     
    // <editor-fold defaultstate="collapsed" desc="Boolean beforeDeleteFromListXhtml()">
    @Override
    public Boolean beforeDeleteFromListXhtml() {
        Boolean delete = userServices.isDeleted(user);
        if (!delete) {
            JsfUtil.warningDialog(rf.getMessage("warning.advertencia"), rf.getMessage("warning.nosepuedeeliminar"));
        }
        return delete;
    }

    // </editor-fold>   
    // <editor-fold defaultstate="collapsed" desc="completeFiltrado(String query)">
    /**
     * Se usa para los autocomplete filtrando
     *
     * @param query
     * @return
     */
    public List<Profile> completeFiltrado(String query) {
        List<Profile> suggestions = new ArrayList<>();
        List<Profile> temp = new ArrayList<>();
        try {
            Boolean found = false;
            query = query.trim();
            String field = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("field");
            temp = profileRepository.findRegex(field, query, true, new Document(field, 1));

            if (profilesList.isEmpty()) {
                if (!temp.isEmpty()) {
                    suggestions = temp;
                }
            } else {
                if (!temp.isEmpty()) {

                    for (Profile r : temp) {
                        found = false;
                        for (Profile r2 : profilesList) {
                            if (r.getIdprofile().equals(r2.getIdprofile())) {
                                found = true;
                            }
                        }
                        if (!found) {
                            suggestions.add(r);
                        }

                    }
                }

            }
            //suggestions=  profileRepository.findRegex(field,query,true,new Document(field,1));

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return suggestions;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String printAll()">
    @Override
    public String printAll() {

//        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4.rotate());
        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            //METADATA

            document.open();
            document.add(ReportUtils.paragraph("USER", FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            Date currentDate = new Date();
            String texto = "Fecha " + DateUtil.showDate(currentDate);
            document.add(ReportUtils.paragraph(texto, FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            String date = DateUtil.showDate(currentDate) + " " + DateUtil.showHour(currentDate);

            document.add(ReportUtils.paragraph("Fecha: " + date, FontFactory.getFont("arial", 8, Font.BOLD), Element.ALIGN_RIGHT));
            document.add(new Paragraph("\n"));

            //Numero de columnas
            PdfPTable table = new PdfPTable(3);

//Aqui indicamos el tamaño de cada columna
            table.setTotalWidth(new float[]{140, 140, 140});

            table.setLockedWidth(true);

            table.addCell(ReportUtils.PdfCell("Username", FontFactory.getFont("arial", 11, Font.BOLD), Element.ALIGN_CENTER));
            table.addCell(ReportUtils.PdfCell("Name", FontFactory.getFont("arial", 11, Font.BOLD), Element.ALIGN_CENTER));

            table.addCell(ReportUtils.PdfCell("Profiles", FontFactory.getFont("arial", 11, Font.BOLD), Element.ALIGN_CENTER));

            for (User u : userList) {
                String profile = "";
               profile = u.getProfile().stream().map((r) -> r.getProfile()+ " ").reduce(profile, String::concat);

                table.addCell(ReportUtils.PdfCell(u.getUsername(), FontFactory.getFont("arial", 10, Font.NORMAL)));
                table.addCell(ReportUtils.PdfCell(u.getName(), FontFactory.getFont("arial", 9, Font.NORMAL)));
              
                table.addCell(ReportUtils.PdfCell(profile, FontFactory.getFont("arial", 10, Font.NORMAL)));

            }
            document.add(table);
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        document.close();

        ReportUtils.printPDF(baos);
        return "";
    }

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="String print">
    @Override
    public String print() {

        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            //METADATA

            document.open();
            document.add(ReportUtils.paragraph("USUARIO", FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            Date currentDate = new Date();
            String texto = "REPORT";
            document.add(ReportUtils.paragraph(texto, FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            String date = DateUtil.showDate(currentDate) + " " + DateUtil.showHour(currentDate);

            document.add(ReportUtils.paragraph("Date: " + date, FontFactory.getFont("arial", 8, Font.BOLD), Element.ALIGN_RIGHT));
            document.add(new Paragraph("\n"));

            document.add(ReportUtils.paragraph("Username: " + user.getUsername(), FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));
            document.add(ReportUtils.paragraph("Name: " + user.getName(), FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));
           
            
            String profile = "";
           profile = user.getProfile().stream().map((r) -> r.getProfile() + " ").reduce(profile, String::concat);
            document.add(ReportUtils.paragraph("Profile: " + profile, FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));

            document.add(ReportUtils.paragraph("Active: " + user.getActive(), FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        document.close();

        ReportUtils.printPDF(baos);
        return "";
    }
    // </editor-fold>   
}
