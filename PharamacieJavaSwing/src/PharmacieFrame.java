import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class PharmacieFrame extends JFrame {
	
	private ArrayList<Client> listeClients = new ArrayList<Client>();//Liste des clients
	private ArrayList<Medicament> listeMedicaments = new ArrayList<Medicament>();//Liste des m�dicaments
	
	private String[] tabClientsTitres = {"Num�ro s�curit� sociale","Nom","Prenom","Adresse","Numero t�l�phone"};
	private String[] tabMedicamentsTitres = {"Reference","Libelle","Description","Quantite","Prix"};
	
	private JPanel transactionPanneau;//Panneau transaction
	private JPanel medicamentPanneau; //Panneau m�dicament
	private JPanel clientPanneau;	  //Panneau client
	private JTabbedPane onglets;	  //Onlets de tableaux
	
	
	
	//Variables panneau client
	static boolean signal = true;
	ArrayList<String> parametre = new ArrayList<String>();
	static int x = 0;
	Object Tab[] = {"Nom","Pr�nom","Adresse","Num t�l�phone","Num S�curit� social"};
	
	
	public PharmacieFrame() {
		
		super("Pharmacie");							// Constructuer de la page avec le titre instanci�
		setDefaultCloseOperation(EXIT_ON_CLOSE); 	// D�finie la fermeture de la page
		setSize(800, 800); 							// D�finie la taille de la page
		setResizable(false); 						// Bloque le redimensionnement de la page
		setLocationRelativeTo(null); 				// D�finie la position de la page de base

		
		
		
		//Pour les tests des clients sont d�j� cr��s
		listeClients.add(new Client("Boulan","Maxime","Lille","0700000000","1234567890123"));
		listeClients.add(new Client("Morel","Quentin","Lille","0600000000","1234567890124"));
		
		//Pour les tests des m�dicaments sont d�j� cr��s
		listeMedicaments.add(new Medicament("ref04923432","Medic pour Quentin","Medicament mal de t�te",2,3.10));
		listeMedicaments.add(new Medicament("ref13212141","Medic pour Maxime","M�dicament mal du foie",2,5.35));
		
		
		
		
		/*-----------------------------------------------Panneau/onglet Transaction------------------------------------------------*/
		
		transactionPanneau = new JPanel();//Cr�ation panneau transaction pharmacie
		
		transactionPanneau.setLayout(null);//D�finition du placement des pbjets manuellement
		JLabel labelEffectuerTransaction = new JLabel("Effectuer une transaction :");//appel du constructeur du label avec un message
		labelEffectuerTransaction.setFont(labelEffectuerTransaction.getFont().deriveFont(30.0f));//D�fini la taille du text du label
		labelEffectuerTransaction.setSize(labelEffectuerTransaction.getPreferredSize().width,labelEffectuerTransaction.getPreferredSize().height);//Adapte la taille du label au texte
		labelEffectuerTransaction.setLocation((this.getWidth()-labelEffectuerTransaction.getWidth())/2, 10);//D�fini la localisation du label
		transactionPanneau.add(labelEffectuerTransaction);//Ajoute le label au panneau transaction
		
		
		
		JLabel lblTransacSecuClient = new JLabel("Num�ro s�curit� sociale :");//Cr�ation label par appel du construcuteur et d�finition du texte
		lblTransacSecuClient.setFont(lblTransacSecuClient.getFont().deriveFont(16.0f));//D�finition de la taille du label
		lblTransacSecuClient.setSize(lblTransacSecuClient.getPreferredSize().width,lblTransacSecuClient.getPreferredSize().height);//Adapte la taille du label au texte
		lblTransacSecuClient.setLocation(0, 65);//D�fini l'emplacement de label
		transactionPanneau.add(lblTransacSecuClient);//ajout du label au panneau transaction
		
		JTextField txtTransacSecuClient = new JTextField();//Cr�ation d'un JtextField 
		txtTransacSecuClient.setSize(200,30);//D�finition de la taille du JtextField 
		txtTransacSecuClient.setLocation(0, 100);//D�fini l'emplacement du JtextField 
		transactionPanneau.add(txtTransacSecuClient);//Ajoute le JtextField au panneau transaction
		
		
		
		JLabel lblTransacRefMedic = new JLabel("R�f�rence m�dicament :");
		lblTransacRefMedic.setFont(lblTransacRefMedic.getFont().deriveFont(16.0f));
		lblTransacRefMedic.setSize(lblTransacRefMedic.getPreferredSize().width,lblTransacRefMedic.getPreferredSize().height);
		lblTransacRefMedic.setLocation(300, 65);
		transactionPanneau.add(lblTransacRefMedic);
		
		JTextField txtTransacRefMedic = new JTextField();
		txtTransacRefMedic.setSize(200,30);
		txtTransacRefMedic.setLocation(300,100);
		transactionPanneau.add(txtTransacRefMedic);
		
		
		
		JLabel lblTransacQuantMedic = new JLabel("Quantit� d�sir�e :");
		lblTransacQuantMedic.setFont(lblTransacQuantMedic.getFont().deriveFont(16.0f));
		lblTransacQuantMedic.setSize(lblTransacQuantMedic.getPreferredSize().width,lblTransacQuantMedic.getPreferredSize().height);
		lblTransacQuantMedic.setLocation(600, 65);
		transactionPanneau.add(lblTransacQuantMedic);
		
		JTextField txtTransacQuantMedic = new JTextField();
		txtTransacQuantMedic.setSize(200,30);
		txtTransacQuantMedic.setLocation(600,100);
		transactionPanneau.add(txtTransacQuantMedic);
		
		
		JLabel lblPrixTransacMedic = new JLabel("Prix du ou des m�dicaments achet�es : ");
		lblPrixTransacMedic.setFont(lblPrixTransacMedic.getFont().deriveFont(16.0f));
		lblPrixTransacMedic.setSize(lblPrixTransacMedic.getPreferredSize().width,lblPrixTransacMedic.getPreferredSize().height);
		lblPrixTransacMedic.setLocation((800-lblPrixTransacMedic.getPreferredSize().width)/2, 150);
		transactionPanneau.add(lblPrixTransacMedic);
		
		
		
		/*-----------------------------------------------Panneau/onglet M�dicament------------------------------------------------*/
		
		
		DefaultTableModel tableModelClient = new DefaultTableModel(tabClientsTitres,0);	
		JTable tableTransacClient = new JTable(tableModelClient);
		//tableTransacClient.setDefaultEditor(Object.class, null);//Empeche modification par l'utilisatuer du tableau affich�
		
		JScrollPane jsTableClient = new JScrollPane(tableTransacClient);		
		jsTableClient.setSize(800, 200);
		jsTableClient.setLocation(0, 300);
		for (int i=0;i<listeClients.size();i++)
		{
			tableModelClient.addRow(arrayEnTabClients(listeClients,i));
		}
		transactionPanneau.add(jsTableClient);
		
		
		DefaultTableModel tableModelMedicament = new DefaultTableModel(tabMedicamentsTitres,0);
		JTable tableTransacMedic = new JTable(tableModelMedicament);
		
		JScrollPane jsTableMedic = new JScrollPane(tableTransacMedic);		
		jsTableMedic.setSize(800, 200);
		jsTableMedic.setLocation(0, 520);
		for (int i=0;i<listeMedicaments.size();i++)
		{
			tableModelMedicament.addRow(arrayEnTabMedicaments(listeMedicaments, i));
		}
		transactionPanneau.add(jsTableMedic);
		
		
		JButton boutonTransacValider = new JButton("Valider transaction");
		boutonTransacValider.setFont(labelEffectuerTransaction.getFont().deriveFont(18.0f));
		boutonTransacValider.setSize(200, 50);
		boutonTransacValider.setLocation(300, 200);
		boutonTransacValider.addActionListener( new ActionListener() {
		       private Component medicamentPanneau;

			public void actionPerformed(ActionEvent e) {
		    	   try {
		    		   verifValeur(txtTransacQuantMedic.getText());
			    	   int numMedic = clientAcheteMedicament(listeClients,listeMedicaments,txtTransacSecuClient.getText(), txtTransacRefMedic.getText(), Integer.parseInt(txtTransacQuantMedic.getText()));

				    	   tableModelMedicament.setValueAt(listeMedicaments.get(numMedic).getQuantite(), numMedic,3 );		    	   
				    	   
				    	   lblPrixTransacMedic.setText("Prix du ou des m�dicaments achet�es : "+arrondi(Integer.parseInt(txtTransacQuantMedic.getText())*listeMedicaments.get(numMedic).getPrix(), 2));
				    	   lblPrixTransacMedic.setSize(lblPrixTransacMedic.getPreferredSize().width,lblPrixTransacMedic.getPreferredSize().height);
				    	   lblPrixTransacMedic.setLocation((800-lblPrixTransacMedic.getPreferredSize().width)/2, 150);

			    	   
			    	   if (listeMedicaments.get(numMedic).getQuantite()==0)
			    	   {
			    		   JOptionPane.showMessageDialog(this.medicamentPanneau,"Le stock du m�dicament est � pr�sent de 0.","Stock vide",JOptionPane.INFORMATION_MESSAGE);
			    	   }
		    	   }
		    	   catch (ExceptionValeurNegaOuNulle except)
		    	   {
		    		   except.printStackTrace();
		    	   }
		    	   catch (Exception except)
		    	   {
		    		   except.printStackTrace();
		    	   }
		       }
		      }
		      );
		transactionPanneau.add(boutonTransacValider);
		
		

		
		
		
		/*-----------------------------------------------Panneau/onglet M�dicament------------------------------------------------*/

		
		medicamentPanneau = new JPanel();//Cr�ation panneau medicament	
		
		medicamentPanneau.setLayout(null);
		//Cr�ation client
		JLabel labelCreationMedic = new JLabel("Cr�ation m�dicament :");
		labelCreationMedic.setFont(labelCreationMedic.getFont().deriveFont(16.0f));
		labelCreationMedic.setSize(labelCreationMedic.getPreferredSize().width,labelCreationMedic.getPreferredSize().height);
		labelCreationMedic.setLocation(10,10);
		medicamentPanneau.add(labelCreationMedic);
		
		
		JLabel lblMedicReference = new JLabel("R�f�rence m�dicament :");
		lblMedicReference.setFont(lblMedicReference.getFont().deriveFont(12.0f));
		lblMedicReference.setSize(lblMedicReference.getPreferredSize().width,lblMedicReference.getPreferredSize().height);
		lblMedicReference.setLocation(10,40);
		medicamentPanneau.add(lblMedicReference);
		
		JTextField textFieldReference = new JTextField();
		textFieldReference.setSize(200,30);
		textFieldReference.setLocation(10,55);
		medicamentPanneau.add(textFieldReference);
		
		
		JLabel lblMedicLibelle = new JLabel("Libell� :");
		lblMedicLibelle.setFont(lblMedicLibelle.getFont().deriveFont(12.0f));
		lblMedicLibelle.setSize(lblMedicLibelle.getPreferredSize().width,lblMedicLibelle.getPreferredSize().height);
		lblMedicLibelle.setLocation(10,90);
		medicamentPanneau.add(lblMedicLibelle);
		
		JTextField textFieldLibelle = new JTextField("");
		textFieldLibelle.setSize(200,30);
		textFieldLibelle.setLocation(10,105);
		medicamentPanneau.add(textFieldLibelle);
		
		
		JLabel lblMedicDescription = new JLabel("Description :");
		lblMedicDescription.setFont(lblMedicDescription.getFont().deriveFont(12.0f));
		lblMedicDescription.setSize(lblMedicDescription.getPreferredSize().width,lblMedicDescription.getPreferredSize().height);
		lblMedicDescription.setLocation(10,135);
		medicamentPanneau.add(lblMedicDescription);
		
		JTextField textFieldDescription = new JTextField();
		textFieldDescription.setSize(200,30);
		textFieldDescription.setLocation(10,150);
		medicamentPanneau.add(textFieldDescription);
		
		
		JLabel lblMedicQuantite = new JLabel("Quantite");
		lblMedicQuantite.setFont(lblMedicQuantite.getFont().deriveFont(12.0f));
		lblMedicQuantite.setSize(lblMedicQuantite.getPreferredSize().width,lblMedicQuantite.getPreferredSize().height);
		lblMedicQuantite.setLocation(10,185);
		medicamentPanneau.add(lblMedicQuantite);
		
		JTextField textFieldQuantite = new JTextField();
		textFieldQuantite.setSize(200,30);
		textFieldQuantite.setLocation(10,200);
		medicamentPanneau.add(textFieldQuantite);		
		
		
		JLabel lblMedicPrix = new JLabel("Prix :");
		lblMedicPrix.setFont(lblMedicPrix.getFont().deriveFont(12.0f));
		lblMedicPrix.setSize(lblMedicPrix.getPreferredSize().width,lblMedicPrix.getPreferredSize().height);
		lblMedicPrix.setLocation(10,235);
		medicamentPanneau.add(lblMedicPrix);
		
		JTextField textFieldPrix = new JTextField();
		textFieldPrix.setSize(200,30);
		textFieldPrix.setLocation(10,250);
		medicamentPanneau.add(textFieldPrix);
		
		
		JButton boutonValiderMedicament = new JButton("Valider cr�ation m�dicament");
		boutonValiderMedicament.setFont(boutonValiderMedicament.getFont().deriveFont(12.0f));
		boutonValiderMedicament.setSize(200,50);
		boutonValiderMedicament.setLocation(10,300);
		boutonValiderMedicament.addActionListener( new ActionListener() {
		       private Component medicamentPanneau;

			public void actionPerformed(ActionEvent e) {
		    	   
		    	   try
		    	   {
		    	   boolean medicExisteDeja = verifMedicExiste(listeMedicaments,textFieldReference.getText());
		    	   
		    	   if (medicExisteDeja==false)
		    	   {
		    		   listeMedicaments.add(new Medicament(textFieldReference.getText(),textFieldLibelle.getText(),textFieldDescription.getText(),Integer.parseInt(textFieldQuantite.getText()),Double.parseDouble(textFieldPrix.getText())));
			           tableModelMedicament.addRow(arrayEnTabMedicaments(listeMedicaments, listeMedicaments.size()-1));
			           JOptionPane.showMessageDialog(this.medicamentPanneau,"Cr�ation du m�dicament faite.","M�dicament cr��",JOptionPane.INFORMATION_MESSAGE);
		    	   }
		    	   else
		    	   {
		    		   
		    	   }
		    	   }
		    	   catch(Exception except)
		    	   {
		    		   except.printStackTrace();
		    	   }
		       }
		      }
		      );
		medicamentPanneau.add(boutonValiderMedicament);

		
		
		//Approvisionner m�dicament
		
		JLabel labelApprovisionnerMedic = new JLabel("Approvisionner m�dicament :");
		labelApprovisionnerMedic.setFont(labelApprovisionnerMedic.getFont().deriveFont(16.0f));
		labelApprovisionnerMedic.setSize(labelApprovisionnerMedic.getPreferredSize().width,labelApprovisionnerMedic.getPreferredSize().height);
		labelApprovisionnerMedic.setLocation(550,10);
		medicamentPanneau.add(labelApprovisionnerMedic);
		
		
		JLabel lblApproMedicRef = new JLabel("Reference");
		lblApproMedicRef.setFont(lblApproMedicRef.getFont().deriveFont(12.0f));
		lblApproMedicRef.setSize(lblApproMedicRef.getPreferredSize().width,lblApproMedicRef.getPreferredSize().height);
		lblApproMedicRef.setLocation(575,85);
		medicamentPanneau.add(lblApproMedicRef);
		
		JTextField txtApproMedicRef = new JTextField();
		txtApproMedicRef.setSize(200,30);
		txtApproMedicRef.setLocation(575,100);
		medicamentPanneau.add(txtApproMedicRef);
		
		
		JLabel lblApproMedicQuant = new JLabel("Quantit�");
		lblApproMedicQuant.setFont(lblApproMedicQuant.getFont().deriveFont(12.0f));
		lblApproMedicQuant.setSize(lblApproMedicQuant.getPreferredSize().width,lblApproMedicQuant.getPreferredSize().height);
		lblApproMedicQuant.setLocation(575,185);
		medicamentPanneau.add(lblApproMedicQuant);
		
		JTextField txtApproMedicQuant = new JTextField();
		txtApproMedicQuant.setSize(200,30);
		txtApproMedicQuant.setLocation(575,200);
		medicamentPanneau.add(txtApproMedicQuant);
		
		
		JButton boutonValiderAppro = new JButton("Valider approvisionnement");
		boutonValiderAppro.setFont(boutonValiderAppro.getFont().deriveFont(12.0f));
		boutonValiderAppro.setSize(200, 50);
		boutonValiderAppro.setLocation(575,300);
		boutonValiderAppro.addActionListener( new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		   		
		    	   try
		    	   {
		    		   verifValeur(txtApproMedicQuant.getText());
		    		   
		    	   int numMedic=approvisionnerMedicament(listeMedicaments,txtApproMedicRef.getText(), Integer.parseInt(txtApproMedicQuant.getText()));
		    	   //tableModelMedicament.
		    	   
		    	   if (numMedic==-1)
		    	   {
		    		   //Afficher message m�doc non trouv�
		    	   }
		    	   else
		    	   {
		    		   	tableModelMedicament.setValueAt(listeMedicaments.get(numMedic).getQuantite(), numMedic,3 );
		    	   }
		    	   }
		    	   catch(ExceptionValeurNegaOuNulle except)
		    	   {
		    		   except.printStackTrace();
		    	   }
		    	   catch (Exception except)
		    	   {
		    		   except.printStackTrace();
		    	   }

		         }
		      }
		      );
		medicamentPanneau.add(boutonValiderAppro);
		
		
		
		
		
		//Chercher m�dicament
		
		JLabel labelChercherMedic = new JLabel("Chercher m�dicament :");
		labelChercherMedic.setFont(labelChercherMedic.getFont().deriveFont(16.0f));
		labelChercherMedic.setSize(labelChercherMedic.getPreferredSize().width,labelChercherMedic.getPreferredSize().height);
		labelChercherMedic.setLocation((800-labelChercherMedic.getPreferredSize().width)/2,400);
		medicamentPanneau.add(labelChercherMedic);
		
		
		JTextField txtChercherMedic = new JTextField();
		txtChercherMedic.setSize(200,30);
		txtChercherMedic.setLocation(100,450);
		medicamentPanneau.add(txtChercherMedic);
		
		
		JTextArea jtaChercherMedic = new JTextArea();
		jtaChercherMedic.setFont(jtaChercherMedic.getFont().deriveFont(20f));
		jtaChercherMedic.setSize(800,200);
		jtaChercherMedic.setLocation(0, 520);
		medicamentPanneau.add(jtaChercherMedic);
		
		
		JButton btnValiderRechercheMedic = new JButton("Valider recherche");
		btnValiderRechercheMedic.setFont(btnValiderRechercheMedic.getFont().deriveFont(14.0f));
		btnValiderRechercheMedic.setSize(200, 50);
		btnValiderRechercheMedic.setLocation(500,440);
		btnValiderRechercheMedic.addActionListener( new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   
		    	   int numMedic=chercherNumMedic(listeMedicaments,txtChercherMedic.getText());
		    	   
		    	   if (numMedic==-1)
		    	   {
		    		   jtaChercherMedic.setText("");
		    		   jtaChercherMedic.append("M�dicament non trouv�");
		    	   }
		    	   else
		    	   {			    			
		    		   jtaChercherMedic.setText("");
		    		   jtaChercherMedic.append("R�ference : "+listeMedicaments.get(numMedic).getReference()+"\n");
		    		   jtaChercherMedic.append("Libell� : "+listeMedicaments.get(numMedic).getLibelle()+"\n");
		    		   jtaChercherMedic.append("Description : "+listeMedicaments.get(numMedic).getDescription()+"\n");
		    		   jtaChercherMedic.append("Quantit� : "+listeMedicaments.get(numMedic).getQuantite()+"\n");
		    		   jtaChercherMedic.append("Prix : "+listeMedicaments.get(numMedic).getPrix());
		    	   }
		    	   
		         }
		      }
		      );
		medicamentPanneau.add(btnValiderRechercheMedic);
		

		
		
		/*-----------------------------------------------Panneau/onglet Client------------------------------------------------*/		
		
		clientPanneau = new JPanel();
		
		clientPanneau.setLayout(null);
		
		

		JTable tableau = new JTable(tableModelClient);
		tableau.setSize(800, 200);	        

        
        JScrollPane js = new JScrollPane(tableau);
        js.setSize(800, 200);	
        js.setLocation(0, 300);	 
        
        JButton valider= new JButton();
		valider.setText("Valider");
		valider.setLocation(80,80);
		valider.setSize(100,20);
		
		
		DefaultTableModel tableModelMedicsClient = new DefaultTableModel(tabMedicamentsTitres,0);
		JTable tableMedicsClient = new JTable(tableModelMedicsClient);

		
		JScrollPane jsTableMedicsClient = new JScrollPane(tableMedicsClient);		
		jsTableMedicsClient.setSize(800, 200);
		jsTableMedicsClient.setLocation(0, 520);
		
		
		JTextField inserer = new JTextField();//JTextField pour ins�rer les informations clientes
		inserer.setSize(100,20);
		inserer.setLocation(80, 100);
		
		//Cr�ation d'un JTextArea pour renseigner les informations � entre lors de la cr�ation/recherche client
		JTextArea Systm = new JTextArea();//JTeaxtArea est un JTesxtField sur plusieurs lignes
		Systm.setSize(300,200);
		Systm.setLocation(410,80);
		Systm.setEditable(false);
		Systm.setText("inserez le nom du nouveaux client");
		valider.addActionListener( new ActionListener() {		
			private Component clientPanneau;

			public void actionPerformed(ActionEvent e) {
		
			if(signal == true) //Signal d�fini si on cr�e ou cherche un client (si signal=true alors on cr�e un client sinon on cherche un client)
			{	
				parametre.add(creercliant(inserer));//On ajoute daans une arraylist les param�tres pour la cr�ation d'un client (rentr� dans un text box)
				
				Systm.append("\n");
				x += 1;
				
				if(x==5) //x corerespond au nombre de fois qu'on appuit sur le bouton "valider", cette variable n'est utilis� que si le signal=true 
                {
					/*
					 * Dans cet condition, on v�rifie que le num�ro de s�cu sociale n'est pas d�j� utilis� par un autre client
					 */
					try 
					{
					verifNumSecu(parametre.get(4));
					
                    if(chercherNumClient(listeClients, parametre.get(4))==-1)
                    { 
                    	/*
                    	 * Dans le cas o� le client n'existe pas alors ce dernier est ajout� � la liste des clients et le tableaux des clients est mis � jour
                    	 */
                    	listeClients.add(new Client(parametre.get(0),parametre.get(1),parametre.get(2),parametre.get(3),parametre.get(4)));
                    
                    	x = 0;
                    	tableModelClient.addRow(arrayEnTabClients(listeClients,listeClients.size()-1));

                    	for (int t=0;t<5;t++)
                    	{
                    		parametre.remove(0);
                    	}
                    	JOptionPane.showMessageDialog(this.clientPanneau,"Cr�ation du client faite.","Client cr��",JOptionPane.INFORMATION_MESSAGE);
                    	Systm.setText("");
                    	
                    }
                    else
                    {
                    	/*
                    	 * Dans le cas o� le client existe, le dernier param�tre � rentrer (le num�ro de s�curit� sociale) est supprim�, il faudra pour rajouter le client rerentrer le num�ro de s�curit� sociale
                    	 * cependant les autres param�tres ne seront pas � r��crire.
                    	 */
                    	Systm.append("\n"+"erreur, ce Num�ro de s�curit� existe d�j�"+"\n");
                    	parametre.remove(4);
                    	x=4;
                    }
					}
                    catch (ExceptionNumSecuNonValide except)
                    {
                    	except.printStackTrace();
                    	
                    	for (int zeta=0;zeta<parametre.size();zeta++) 
                    	{
                        	parametre.remove(0);
                    	}
                    	Systm.setText("");
                    	x=0;
                    
                    }
                    
                }

                Systm.append("inserez " + (String)Tab[x] + " du nouveaux client");
            }	
		

				
			if (signal==false)
			{
				/*
				 * Dans le cas o� l'on cherche le client. Via la fonction chercherNumClient, on recherhe l'indice de la liste du client correspondant 
				 * au num�ro de s�curit� donn�e dans le JTextField.
				 */
				int numClientRecherche = chercherNumClient(listeClients,inserer.getText());
				
				if (numClientRecherche==-1)
				{
					//afficher erreur
				}
				else
				{
					/*
					 * Affichage du client
					 */
					Systm.setText("");
					Systm.append("Nom : "+listeClients.get(numClientRecherche).getNom()+"\n");
					Systm.append("Prenom : "+listeClients.get(numClientRecherche).getPrenom()+"\n");
					Systm.append("Adresse : "+listeClients.get(numClientRecherche).getAdresse()+"\n");
					Systm.append("Numero de t�l�phone : "+listeClients.get(numClientRecherche).getNumeroTelephone()+"\n");
					Systm.append("Num�ro de s�curit� : "+listeClients.get(numClientRecherche).getNumSecuSociale()+"\n");
					
					
					tableModelMedicsClient.setRowCount(0);

					//Mise � jour du tableaux de la liste de l'historique des transactions du client
					for (int z=0;z<listeClients.get(numClientRecherche).getListeMedicamentClient().size();z++)
					{
						tableModelMedicsClient.addRow(arrayEnTabMedicaments(listeClients.get(numClientRecherche).getListeMedicamentClient(), z));
					}
				}
				
			}
			
			}
		});
		
		JButton change = new JButton();
		change.setText("Chercher client par son num�ro de s�curit� social");
		change.setLocation(200,80);
		change.setSize(200,200);
		change.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				/*
				 * L'appuis sur ce bouton permet le changement de mode entre cr�ation client et recherche d'un client.
				 * Lorsqu'on appuis sur le bouton les informations de cr�atio d'un client non fini sont remises � 0.
				 */
			signal = signalvalue(signal, Systm);
			
			if (signal==false && x!=5) {
                for (int t=0;t<parametre.size();t++)
                {
                    parametre.remove(0);
                }
                x = 0;
            }
			change.setText(bchange(signal));
   }
}
);
		
		JLabel lblPanneauClient = new JLabel("Cr�ation et recherche de clients :");
		lblPanneauClient.setFont(lblPanneauClient.getFont().deriveFont(30.0f));
		lblPanneauClient.setSize(lblPanneauClient.getPreferredSize().width,lblPanneauClient.getPreferredSize().height);
		lblPanneauClient.setLocation((800-lblPanneauClient.getPreferredSize().width)/2,10);
		clientPanneau.add(lblPanneauClient);
		
		JLabel lblhistoMedicsClient = new JLabel("Historique des achats du client recherch� :");
		lblhistoMedicsClient.setFont(lblhistoMedicsClient.getFont().deriveFont(16.0f));
		lblhistoMedicsClient.setSize(lblhistoMedicsClient.getPreferredSize().width,lblhistoMedicsClient.getPreferredSize().height);
		lblhistoMedicsClient.setLocation((800-lblhistoMedicsClient.getPreferredSize().width)/2,497);
		clientPanneau.add(lblhistoMedicsClient);
		
		clientPanneau.add(jsTableMedicsClient);	
		clientPanneau.add(valider);
		clientPanneau.add(change);
		clientPanneau.add(inserer);
		clientPanneau.add(js);
		clientPanneau.add(Systm);		

		
		onglets = new JTabbedPane(SwingConstants.TOP);//Cr�e un objet de type JTabbedPane soit des onglets de panneau
		onglets.add("Transaction",transactionPanneau);//Ajout du panneau transactionPanneau � la liste d'onglets
		onglets.add("Medicament",medicamentPanneau);//Ajout du panneau medicamentPanneau � la liste d'onglets
		onglets.add("Client",clientPanneau);//Ajout du panneau clientPanneau � la liste d'onglets
		add(onglets);//Ajoute le JTabbedPane cr�� avec ses onglets � la page JFrame
		
		setVisible(true); // Affiche notre page PharmacieFrame
	}
	

	
	
	
	/*----------------------------------------------Fonction Client-------------------------------------------------------*/
	
	public String bchange(boolean a) {
		/*
		 * La fonction permet de changer le texte du bouton sur page client
		 */
		String text;
		
		if(a == true)
			text = "ajouter client";
		else 
			text ="chercher client par son num�ro de s�curit� social";
		
		return text;
	}
	
	
	public String creercliant(JTextField i){ 
			String chr = i.getText();
			i.setText("");
			return chr;
			}
	public boolean signalvalue(boolean a, JTextArea j) {
		if(a == true) {
			a = false;
			j.setText("inserez le Num�ro de s�curit� rechercher");
		}
		else {
			a = true;
			j.setText("Ins�rez le Nom du nouveaux client");
		}
		return a;
		}

	public boolean VerifNumSecu(String num) {
		if(num.length() < 13) {
			return false;
		}
		else {
			return true;}
		}
	
	//----------------------------------------------Fonction pour JTables--------------------------------------------------
	
	public static Object[] arrayEnTabClients(ArrayList<Client> LClients,int y)
    {
		/*
		 * Fonction de conversion de la liste de clients envoy� en param�tre en Objet Tab
		 */
        Object [] tabTempo = new Object[5];
            tabTempo[0]= String.valueOf(LClients.get(y).getNumSecuSociale());
            tabTempo[1]= LClients.get(y).getNom();
            tabTempo[2]= LClients.get(y).getPrenom();
            tabTempo[3]= LClients.get(y).getAdresse();
            tabTempo[4]= LClients.get(y).getNumeroTelephone();


        return tabTempo;
    }
	
	public static Object[] arrayEnTabMedicaments(ArrayList<Medicament> LMedics,int y)
    {		
		/*
		 * Fonction de conversion de la liste de m�dicaments envoy� en param�tre en Objet Tab
		 */
        Object [] tabTempo = new Object[5];
            tabTempo[0]= LMedics.get(y).getReference();
            tabTempo[1]= LMedics.get(y).getLibelle();
            tabTempo[2]= LMedics.get(y).getDescription();
            tabTempo[3]= String.valueOf(LMedics.get(y).getQuantite());
            tabTempo[4]= String.valueOf(LMedics.get(y).getPrix());


        return tabTempo;
    }
	
	
	
	
	/*------------------------------------------------Fonctions Classe Client et M�dicament------------------------------------------------------------*/
	
	
	
	public static void creerClient(ArrayList<Client> listeClients,String nom, String prenom, String adresse, String numeroTelephone,String numSecuSociale)
	{
		
		//V�rification des donn�es � faire
		
		listeClients.add(new Client(nom, prenom, adresse, numeroTelephone,numSecuSociale));
	}
	
	public static void creerMedicament(ArrayList<Medicament> listeMedicaments,String reference, String libelle, String description, int quantite, double prix)
	{
		//V�rification des donn�es � faire
		
		listeMedicaments.add(new Medicament(reference, libelle, description, quantite, arrondi(prix,2)));
		
		//Confirmation ajout avec messagebox
	}
	
	public static int approvisionnerMedicament(ArrayList<Medicament> listeMedicaments,String reference, int quantiteAjoutee)
	{
		/*
		 * Fonction d'approvisionnement d'un m�dicament
		 * 
		 * La fonction parcours la liste des m�dicaments re�u en param�tre et recherche le m�dicament
		 * avec la r�f�rence identique � celle envoy� en param�tre afin de pouvoir ajouter la quantit�
		 * souhait� au stocke du m�dicament
		 */
		boolean medicamentTrouve=false;
		int numMedicTrouve=-1;
		
		for (int i = 0 ; i < listeMedicaments.size() ; i++)
		{
			
			if (listeMedicaments.get(i).getReference().equals(reference))
			{
				medicamentTrouve=true;
				listeMedicaments.get(i).approvisionnerStock(quantiteAjoutee);
				numMedicTrouve=i;
			}

		}
		
		return numMedicTrouve;
	}
	
	public static int clientAcheteMedicament(ArrayList<Client> listeClients,ArrayList<Medicament> listeMedicaments, String numSecuSociale, String referenceMedicament, int quantiteSouaitee)
	{
		/*
		 * La fonction re�oit en entr�e la liste de clients et de m�dicament ainsi que le num�ro 
		 * de s�cu du client et la r�f�rence du m�dicament voulu. La fonction parcours donc tous
		 * les cleints/m�dicaments � la recherche de ceux indiqu�s en param�tre pour faire l'achat
		 * du m�dicament d'un client et l'ajouter � son historique d'achat.
		 */
		boolean clientTrouve=false,medicamentTrouve=false,medicamentDisponible=false;
		int numMedic=-1;
		
		for (int i=0;i<listeClients.size();i++)
		{
			if (listeClients.get(i).getNumSecuSociale().equals(numSecuSociale))
			{
				clientTrouve=true;
				
				for (int j=0;j<listeMedicaments.size();j++)
				{
					if (listeMedicaments.get(j).getReference().equals(referenceMedicament))
					{
						medicamentTrouve=true;
						
						if (listeMedicaments.get(j).getQuantite()>=quantiteSouaitee)
						{
							medicamentDisponible=true;
							numMedic=j;
							
							listeClients.get(i).acheterMedicaments(listeMedicaments.get(j), quantiteSouaitee);
						}
					}
				}
			}
		}
		
		/*
		 * Inclure gestion des erreurs avec les bool�ens.
		 */
		return numMedic;
	}
	
	static int chercherNumClient(ArrayList<Client> listeClients,String numSecuSociale)
	{
		/*
		 * Fonction de recherche d'un client
		 * 
		 * La fonction parcours la liste de clients envoy� en param�tre et cherche celui avec le m�me
		 * num�ro de s�curit� envoy� en param�tre afin de renvoyer l'indice du client de la liste des clients
		 * en retour de fonction
		 */
		boolean clientTrouve=false;
		int numClienTrouve=-1;
		
		for (int i = 0 ; i <listeClients.size();i++)
		{
			if (listeClients.get(i).getNumSecuSociale().equals(numSecuSociale))
			{
				clientTrouve=true;
				numClienTrouve=i;
			}
		}
		
		/*
		 * Inclure gestion des erreurs avec le boolean.
		 */
		return numClienTrouve;
	}
	
	static int chercherNumMedic(ArrayList<Medicament> listeMedicaments,String referenceMedicament)
	{
		/*
		 * Fonction de recherche d'un m�dicament
		 * 
		 * La fonction parcours la liste de m�dicaments envoy� en param�tre et cherche celui avec la m�me
		 * num�ro r�f�rence envoy�e en param�tre afin de renvoyer l'indice du m�dicament de la liste des m�dicaments
		 * en retour de fonction
		 */
		boolean medicamentTrouve=false;
		int numMedicTrouve=-1;
		
		for (int i=0;i<listeMedicaments.size();i++)
		{
			
			if (listeMedicaments.get(i).getReference().equals(referenceMedicament))
			{
				medicamentTrouve=true;
				numMedicTrouve=i;	
			}
		}

		return numMedicTrouve;	
	}
	
	static boolean verifMedicExiste(ArrayList<Medicament> listeMedicaments,String refMedic)
	{
		/*
		 * Fonction de v�rification de l'existance d'un m�dicament
		 * 
		 * La fonction re�oit en param�tre la liste des m�dicaments et la r�ference d'un m�dicament
		 * souhait� �tre cr��. La focntion parcours la liste des m�dicaments et v�rifie si la r�f�rence 
		 * envoy� en param�tre n'existe pas d�j� sur un autre m�dicament. Si la r�f�rence existe d�j� alors
		 * la fonction renvoie true sinon false.
		 */
		boolean medicExisteDeja=false;
		
		for (int i=0;i<listeMedicaments.size();i++)
		{
			if (listeMedicaments.get(i).getReference().equals(refMedic))
			{
				medicExisteDeja=true;
			}
		}
		
		return medicExisteDeja;
	}
	
	/*-------------------------------------------(Fonctions exceptions)------------------------------------------------------------*/
	
	void verifValeur(String nbMedicTransac) throws ExceptionValeurNegaOuNulle
	{
		/*
		 * Fonction appel exception de valeur n�gative ou nulle
		 */
		int intNbMedicTransac = Integer.parseInt(nbMedicTransac);
		
		if(intNbMedicTransac <= 0)
		{
			throw new ExceptionValeurNegaOuNulle();
		}
	}
	
	void verifNumSecu(String numSecu) throws ExceptionNumSecuNonValide
	{
		/*
		 * Fonction appel exception num�ro de s�curit� sociale inf�rieru � 13 caract�res
		 */
		if (numSecu.length()<13)
		{
			throw new ExceptionNumSecuNonValide();
		}
	}
	
	/*-------------------------------------------(Fonctions suppl�mentaires)------------------------------------------------------------*/
	
	static double arrondi(double valeur, int arrondi) 
	{
		return (double) ( (int) (valeur * Math.pow(10, arrondi) + .5)) / Math.pow(10, arrondi);
	}
}

