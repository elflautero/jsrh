package jsrh;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application{
	
	ObservableList<INTERFERENCIA> obsList;

	public void start(Stage stage) {
		
		WebView w = new WebView();
		WebEngine we = w.getEngine();
		we.load(getClass().getResource("/web/index.html").toExternalForm());
		
		Button btn1 = new Button("button");
		btn1.getStyleClass().add("button");
		
		TableView<INTERFERENCIA> tv = new TableView<INTERFERENCIA>();
		
		//TableColumn<INTERFERENCIA, String> tcIdInt;
		//TableColumn<INTERFERENCIA, String> tcIdEmp;
		//TableColumn<INTERFERENCIA, String> tcIdUH;
		
		
		TableColumn<INTERFERENCIA,String> tcIdInt = new TableColumn<INTERFERENCIA,String>("Id Interferência");
		tcIdInt.setCellValueFactory(new PropertyValueFactory("idInterferencia"));
		tcIdInt.setPrefWidth(250);
		 
		TableColumn<INTERFERENCIA,String> tcIdEmp = new TableColumn<INTERFERENCIA,String>("Id Empreendimento");
		tcIdEmp.setCellValueFactory(new PropertyValueFactory("idEmpreendimento")); 
		tcIdEmp.setPrefWidth(250);
		
		TableColumn<INTERFERENCIA,String> tcIdUH = new TableColumn<INTERFERENCIA,String>("Id UH");
		tcIdUH.setCellValueFactory(new PropertyValueFactory("idUH"));
		tcIdUH.setPrefWidth(250);
		
		tv.getColumns().setAll(tcIdInt, tcIdEmp, tcIdUH);
		
		tv.setLayoutX(10);
		tv.setLayoutY(60);
		tv.setPrefSize(750, 400);
		
		obsList = FXCollections.observableArrayList();
		
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
		    @Override public void handle(ActionEvent e) {
		    	
		    	
		    	// funcionando
		    	List<INTERFERENCIA> iList = listInterferencia(42);
		    	
		    	
		    	for (INTERFERENCIA i : iList) {
		    		
		    		i.getIdInterferencia();
		    		i.getIdEmpreendimento();
		    		
		    		obsList.add(i);
		    		
		    		System.out.println("id inter " + i.getIdInterferencia() + " e di empre " + i.getIdEmpreendimento() + " e id uh " + i.getIdUH());
		    		
		    	}
		    	
		    	
		    	 tv.setItems(obsList);
		    	
		    	System.out.println("tamanho da lista "  + iList.size());
		    	
		    	
		    	/* funcionando
		    	 
		    	 * Hibernate: select 
		    	 * 			this_.ID_INTERFERENCIA as ID_INTER1_0_0_, 
		    	 * 			this_.ID_EMPREENDIMENTO as ID_EMPRE2_0_0_ 
		    	 * 				from [GisDB].[dbo].[temp2interferencia] 
		    	 * 				this_ where 
		    	 * 			this_.ID_INTERFERENCIA like ?

		    	 */
		    	
		    	
		    	/* deu certo
		    	DataBase db = new DataBase();
		    	
		    	db.getConnection();
		    	System.out.println(db.getConnectionUrl());
		    	db.displayDbProperties();
		    	*/
		    	
		    	
		    	/* 
		    	 com.microsoft.sqlserver
		    	 com.microsoft.sqlserver.jdbc.SQLServerDriver
		    	  
		    	 
 				 DriverManager.getConnection("GisDB","ADASA\fabricio.barrozo","fj17000071jf");
		    	
		    	
		    	 Microsoft SQL Server
				 jdbc:weblogic:mssqlserver4:<DB>@<HOST>:<PORT>
				 weblogic.jdbc.mssqlserver4.Driver
		    		
		    	 nome da conexao srvhost4\prod (ADASA\fabricio.barrozo)
		    	 database name GisDB
		    	 logon ADASA\fabricio.barrozo
		    	 servidor srvhost4\prod
		    	 
		    	 */
		    	
		    	
		    
		    }
		});
		
		
		//tcIdInt.setCellValueFactory(cellData -> cellData.getValue().getIdInterferencia());
		
		
		
        Scene scene = new Scene(new Group(w, btn1, tv));
        scene.getStylesheets().add("/css/style.css");

        stage.setTitle("Welcome to JavaFX!"); 
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show(); 
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @SuppressWarnings("unchecked")
	public List<INTERFERENCIA> listInterferencia(int idInt) {
		
		List<INTERFERENCIA> list = new ArrayList<INTERFERENCIA>();
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		
		s.beginTransaction();
		
		// FUNCIONADO CRITEIRA HIBERNATE
		 /*
		Criteria crit = s.createCriteria(INTERFERENCIA.class);
		crit.add(Restrictions.like("idUH", idInt));  // ("ID_INTERFERENCIA", '%' + idInt + '%'));
		
		list = crit.list();
		*/
		
		// FUNCIONANDO SQL QUERY
		
		list = s.createSQLQuery("select * from [GisDB].[dbo].[temp2interferencia] where ID_UH = " + idInt )
				.addEntity(INTERFERENCIA.class)
				.list();
			
		// FUNCIONANDO HQL 
		
		//list = s.createQuery("from INTERFERENCIA I where I.idUH = " + idInt).list();
		
		s.getTransaction().commit();
		s.close();
		return list;
	}
}


