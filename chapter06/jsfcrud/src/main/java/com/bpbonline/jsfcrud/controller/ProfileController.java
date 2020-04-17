/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpbonline.jsfcrud.controller;

// <editor-fold defaultstate="collapsed" desc="imports">
import com.avbravo.jmoordb.configuration.JmoordbConfiguration;
import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordb.configuration.JmoordbControllerEnvironment;
import com.avbravo.jmoordb.interfaces.IController;
import com.avbravo.jmoordb.mongodb.history.services.AutoincrementableServices;
import com.avbravo.jmoordbutils.printer.Printer;

import com.avbravo.jmoordb.mongodb.history.services.ErrorInfoServices;
import com.avbravo.jmoordb.mongodb.repository.Repository;
import com.avbravo.jmoordb.pojos.JmoordbNotifications;
import com.avbravo.jmoordb.profiles.repository.JmoordbNotificationsRepository;
import com.avbravo.jmoordb.services.RevisionHistoryServices;
import com.avbravo.jmoordbutils.DateUtil;
import com.avbravo.jmoordbutils.JmoordbResourcesFiles;
import com.avbravo.jmoordbutils.JsfUtil;
import com.avbravo.jmoordbutils.ReportUtils;
import com.bpbonline.jsfcrud.datamodel.ProfilesDataModel;
import com.bpbonline.jsfcrud.entity.Profile;
import com.bpbonline.jsfcrud.entity.User;
import com.bpbonline.jsfcrud.repository.ProfileRepository;
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
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
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
public class ProfileController implements Serializable, IController {

// <editor-fold defaultstate="collapsed" desc="fields">  
    private static final long serialVersionUID = 1L;

    private Boolean writable = false;
    //DataModel
    private ProfilesDataModel profileDataModel;

    Integer page = 1;
    Integer rowPage = 25;
    List<Integer> pages = new ArrayList<>();

    //Entity
    Profile profile = new Profile();
   Profile profileSelected;
   Profile profileSearch = new Profile();

    //List
    List<Profile> profileList = new ArrayList<>();
     List<Profile> profileListSelected = new ArrayList<>();

    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="reposisitory">
    //Repository
    @Inject
    ProfileRepository profileRepository;

    @Inject
    JmoordbResourcesFiles rf;
    @Inject
    Printer printer;
    // </editor-fold>  
    // <editor-fold defaultstate="collapsed" desc="services">

    //Notification
    @Inject
    UserServices userServices;
    @Inject
    JmoordbNotificationsRepository jmoordbNotificationsRepository;
    //Services
    @Inject
    AutoincrementableServices autoincrementableServices;
    @Inject
    ErrorInfoServices errorServices;
    @Inject
    ProfileServices profileServices;
    @Inject
    @Push(channel = "notification")
    private PushContext push;

    //List of Relations
    //Repository of Relations
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="getter/setter">
    public List<Integer> getPages() {

        return profileRepository.listOfPage(rowPage);
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="constructor">
    public ProfileController() {
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {

            /*
            configurar el ambiente del controller
             */
            HashMap parameters = new HashMap();
            User jmoordb_user = (User) JmoordbContext.get("jmoordb_user");
            //    parameters.put("P_EMPRESA", jmoordb_user.getEmpresa().getDescripcion());

            JmoordbControllerEnvironment jmc = new JmoordbControllerEnvironment.Builder()
                    .withController(this)
                    .withRepository(profileRepository)
                    .withEntity(profile)
                    .withService(profileServices)
                    .withNameFieldOfPage("page")
                    .withNameFieldOfRowPage("rowPage")
                    .withTypeKey("primary")
                    .withSearchLowerCase(false)
                    .withPathReportDetail("/resources/reportes/rol/details.jasper")
                    .withPathReportAll("/resources/reportes/rol/all.jasper")
                    .withparameters(parameters)
                    .withResetInSave(true)
                    .withAction("golist")
                    .build();

            start();
//            String action = "gonew";
//            if (getAction() != null) {
//                action = getAction();
//            }
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean afterSave(Boolean saved)">
    @Override
    public Boolean afterSave(Boolean saved) {
        try {

            for (User u : userServices.getUserList()) {
                //Guardarlo en la base de datos
                JmoordbNotifications jmoordbNotifications = new JmoordbNotifications();
//                User jmoordb_user = (User) JmoordbContext.get("jmoordb_user");
                jmoordbNotifications.setIdjmoordbnotifications(autoincrementableServices.getContador("jmoordbnNotifications"));
                jmoordbNotifications.setUsername(u.getUsername());
                jmoordbNotifications.setMessage("se creo un nuevo rol");
                jmoordbNotifications.setViewed("no");
                jmoordbNotifications.setDate(DateUtil.fechaActual());
                jmoordbNotifications.setType("rolnuevo");
                jmoordbNotificationsRepository.save(jmoordbNotifications);
            }
            push.send("Se creo un nuevo rol");
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        return false;
    }    // </editor-fold> 

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
            profileDataModel = new ProfilesDataModel(profileList);
            Document doc;

            switch (getSearch()) {
                case "_init":
                  profileList  = profileRepository.findPagination(page, rowPage);
                    break;
                case "_autocomplete":
                    break;

                case "idrol":
                    if (getValueSearch() != null) {
                        profileSearch.setIdprofile(getValueSearch().toString());
                        doc = new Document("idrol", profileSearch.getIdprofile());
                       profileList = profileRepository.findPagination(doc, page, rowPage, new Document("idprofile", -1));
                    } else {
                       profileList =profileRepository.findPagination(page, rowPage);
                    }

                    break;

                case "rol":
                    if (getValueSearch() != null) {
                       profileSearch.setProfile(getValueSearch().toString());
                      profileList = profileRepository.findRegexInTextPagination("profile", profileSearch.getProfile(), true, page, rowPage, new Document("profile", -1));

                    } else {
                       profileList = profileRepository.findPagination(page, rowPage);
                    }

                    break;
                case "activo":
                    if (getValueSearch() != null) {
                        profileSearch.setActive(getValueSearch().toString());
                        doc = new Document("activo", profileSearch.getActive());
                       profileList = profileRepository.findPagination(doc, page, rowPage, new Document("idprofile", -1));
                    } else {
                      profileList = profileRepository.findPagination(page, rowPage);
                    }
                    break;

                default:
                   profileList = profileRepository.findPagination(page, rowPage);
                    break;
            }

            profileDataModel = new ProfilesDataModel(profileList);

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);

        }

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean beforeDelete()">
    @Override
    public Boolean beforeDelete() {
        Boolean delete = profileServices.isDeleted(profile);
        if (!delete) {
            JsfUtil.warningDialog(rf.getMessage("warning.advertencia"), rf.getMessage("warning.nosepuedeeliminarrol"));
        }
        return delete;
    }

    // </editor-fold>     
    // <editor-fold defaultstate="collapsed" desc="Boolean beforeDeleteFromListXhtml()">
    @Override
    public Boolean beforeDeleteFromListXhtml() {
        Boolean delete = profileServices.isDeleted(profile);
        if (!delete) {
            JsfUtil.warningDialog(rf.getMessage("warning.advertencia"), rf.getMessage("warning.nosepuedeeliminarrol"));
        }
        return delete;
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
            document.add(ReportUtils.paragraph("ROLES", FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            Date currentDate = new Date();
            String texto = "Fecha " + DateUtil.showDate(currentDate);
            document.add(ReportUtils.paragraph(texto, FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            String date = DateUtil.showDate(currentDate) + " " + DateUtil.showHour(currentDate);

            document.add(ReportUtils.paragraph("Fecha: " + date, FontFactory.getFont("arial", 8, Font.BOLD), Element.ALIGN_RIGHT));
            document.add(new Paragraph("\n"));

            //Numero de columnas
            PdfPTable table = new PdfPTable(3);

//Aqui indicamos el tamaño de cada columna
            table.setTotalWidth(new float[]{140, 140, 45});

            table.setLockedWidth(true);

            table.addCell(ReportUtils.PdfCell("Id", FontFactory.getFont("arial", 11, Font.BOLD), Element.ALIGN_CENTER));
            table.addCell(ReportUtils.PdfCell("Rol", FontFactory.getFont("arial", 11, Font.BOLD), Element.ALIGN_CENTER));
            table.addCell(ReportUtils.PdfCell("Activo", FontFactory.getFont("arial", 11, Font.BOLD), Element.ALIGN_CENTER));

            for (Profile r : profileList) {

                table.addCell(ReportUtils.PdfCell(r.getIdprofile(), FontFactory.getFont("arial", 10, Font.NORMAL)));
                table.addCell(ReportUtils.PdfCell(r.getProfile(), FontFactory.getFont("arial", 9, Font.NORMAL)));
                table.addCell(ReportUtils.PdfCell(r.getActive(), FontFactory.getFont("arial", 10, Font.NORMAL)));

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
            document.add(ReportUtils.paragraph("ROLES", FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            Date currentDate = new Date();
            String texto = "REPORTE";
            document.add(ReportUtils.paragraph(texto, FontFactory.getFont("arial", 12, Font.BOLD), Element.ALIGN_CENTER));

            String date = DateUtil.showDate(currentDate) + " " + DateUtil.showHour(currentDate);

            document.add(ReportUtils.paragraph("Fecha: " + date, FontFactory.getFont("arial", 8, Font.BOLD), Element.ALIGN_RIGHT));
            document.add(new Paragraph("\n"));

            document.add(ReportUtils.paragraph("Id: " + profile.getIdprofile(), FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));
            document.add(ReportUtils.paragraph("Profile: " + profile.getProfile(), FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));
            document.add(ReportUtils.paragraph("Active: " + profile.getActive(), FontFactory.getFont("arial", 12, Font.NORMAL), Element.ALIGN_JUSTIFIED));

        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        document.close();

        ReportUtils.printPDF(baos);
        return "";
    }
    // </editor-fold>  
    
    
     public String deleteSelected() {
        try {
            Integer deleteCount =0;
            if (profileListSelected == null || profileListSelected.isEmpty() || profileListSelected.size() == 0) {
                JsfUtil.warningDialog(rf.getAppMessage("warning.view"), rf.getMessage("warning.nohayseleccionparaeliminar"));
            } else {
                User jmoordb_user = (User) JmoordbContext.get("jmoordb_user");

                for (Profile r : profileListSelected) {
                    if (profileServices.isDeleted(r)) {
                        if (profileRepository.delete("idprofile", r.getIdprofile())) {
                            deleteCount++;
                            //guarda el contenido anterior
                            JmoordbConfiguration jmc = new JmoordbConfiguration();
                            Repository repositoryRevisionHistory = jmc.getRepositoryRevisionHistory();
                            RevisionHistoryServices revisionHistoryServices = jmc.getRevisionHistoryServices();
                            repositoryRevisionHistory.save(revisionHistoryServices.getRevisionHistory(r.getIdprofile().toString(), jmoordb_user.getUsername(),
                                    "delete", "profile", profileRepository.toDocument(r).toString()));
                            
                        }

                    }

                }
                if(deleteCount != profileListSelected.size()){
                     JsfUtil.warningDialog(rf.getAppMessage("info.deleted"), rf.getAppMessage("warning.soloseeliminaron") + " "+deleteCount);
                }else{
                    JsfUtil.infoDialog(rf.getAppMessage("warning.view"),rf.getAppMessage("warning.todosdocumentosseleccionadoseliminados") );
                }

            }
        } catch (Exception e) {
            errorServices.errorMessage(nameOfClass(), nameOfMethod(), e.getLocalizedMessage(), e);
        }
        move(page);
        return "";

    }
}
