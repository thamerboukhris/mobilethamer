/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import entities.Services;
import java.util.ArrayList;
import java.util.Random;
import services.ServiceServices;

/**
 *
 * @author Lord
 */
public class ServicesFront extends Form {
     ArrayList<Services> list=ServiceServices.getInstance().parseservice();
      Form f;
      Button btnMail=new Button("Send Demande service");
      TextField TfDemande = new TextField("","Votre Demande de service");
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  Container C3 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
    public ServicesFront(Form previous){
         f=this;

      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Reclamation");
   
      btnMail.addActionListener(e -> {
      if(TfDemande.getText().length()==0){
          Dialog.show("Error","demande vide","OK",null);
      }
      else{
        Message m = new Message("je demande d'ajouter un nouveau service qui est :");
        m.sendMessage(new String[] {"thameurboukhris8@gmail.com"},TfDemande.getText(), m);
      }
      });
      C3.addAll(TfDemande,btnMail);
      
  TextField searchField = new TextField("", "Search");

  searchField.addDataChangeListener((i1, i2) -> {
    
   String text = searchField.getText();
   
   if(text.length()!=0)    
   {list=ServiceServices.getInstance().parseServiceSearch(text);

 
            C2.removeAll();
               C2.add(C3);
            addservice(list); 
         
            refreshTheme();
    } 
   else{
      list=ServiceServices.getInstance().parseservice(); 
       C2.removeAll(); 
        
       C2.add(C3);
       addservice(list); 
      
       refreshTheme();
   }
       
});
  
  
  C2.add(C3);
    addservice(list);
       
      

addAll(C2);

  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
  getToolbar().setTitleComponent(searchField);
  
    }
    
         public void addservice(ArrayList<Services> list){
         
  for( Services c : list){
       

        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       

Label l = new Label("Nom service: "+c.getNom());
Label l2 = new Label("Nb Freelance: " +c.getNb());

   C1.addAll(l,l2);    
       

C2.addAll(C1);
  
  
      
    
    }
  
    }
}
