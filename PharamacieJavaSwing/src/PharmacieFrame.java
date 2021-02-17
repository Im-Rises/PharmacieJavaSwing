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
	private ArrayList<Medicament> listeMedicaments = new ArrayList<Medicament>();//Liste des médicaments
	
	private String[] tabClientsTitres = {"Numéro sécurité sociale","Nom","Prenom","Adresse","Numero téléphone"};
	private String[] tabMedicamentsTitres = {"Reference","Libelle","Description","Quantite","Prix"};
	
	private JPanel transactionPanneau;//Panneau transaction
	private JPanel medicamentPanneau; //Panneau médicament
	private JPanel clientPanneau;	  //Panneau client
	private JTabbedPane onglets;	  //Onlets de tableaux
	
	
	
	//Variables panneau client
	static boolean signal = true;
	ArrayList<String> parametre = new ArrayList<String>();
	static int x = 0;
	Object Tab[] = {"Nom","Prénom","Adresse","Num téléphone","Num Sécurité social"};
	
	
	public PharmacieFrame() {
		
		super("Pharmacie");							// Constructuer de la page avec le titre instancié
		setDefaultCloseOperation(EXIT_ON_CLOSE); 	// Définie la fermeture de la page
		setSize(800, 800); 							// Définie la taille de la page
		setResizable(false); 						// Bloque le redimensionnement de la page
		setLocationRelativeTo(null); 				// Définie la position de la page de base

		
		
		
		//Pour les tests des clients sont déjà créés
		listeClients.add(new Client("Boulan","Maxime","Lille","0700000000","1234567890123"));
		listeClients.add(new Client("Morel","Quentin","Lille","0600000000","1234567890124"));
		
		//Pour les tests des médicaments sont déjà créés
		listeMedicaments.add(new Medicament("ref04923432","Medic pour Quentin","Medicament mal de tête",2,3.10));
		listeMedicaments.add(new Medicament("ref13212141","Medic pour Maxime","Médicament mal du foie",2,5.35));
		
		
		
		
		/*-----------------------------------------------Panneau/onglet Transaction------------------------------------------------*/
		
		transactionPanneau = new JPanel();//Création panneau transaction pharmacie
		
		transactionPanneau.setLayout(null);//Définition du placement des pbjets manuellement
		JLabel labelEffectuerTransaction = new JLabel("Effectuer une transaction :");//appel du constructeur du label avec un message
		labelEffectuerTransaction.setFont(labelEffectuerTransaction.getFont().deriveFont(30.0f));//Défini la taille du text du label
		labelEffectuerTransaction.setSize(labelEffectuerTransaction.getPreferredSize().width,labelEffectuerTransaction.getPreferredSize().height);//Adapte la taille du label au texte
		labelEffectuerTransaction.setLocation((this.getWidth()-labelEffectuerTransaction.getWidth())/2, 10);//Défini la localisation du label
		transactionPanneau.add(labelEffectuerTransaction);//Ajoute le label au panneau transaction
		
		
		
		JLabel lblTransacSecuClient = new JLabel("Numéro sécurité sociale :");//Création label par appel du construcuteur et définition du texte
		lblTransacSecuClient.setFont(lblTransacSecuClient.getFont().deriveFont(16.0f));//Définition de la taille du label
		lblTransacSecuClient.setSize(lblTransacSecuClient.getPreferredSize().width,lblTransacSecuClient.getPreferredSize().height);//Adapte la taille du label au texte
		lblTransacSecuClient.setLocation(0, 65);//Défini l'emplacement de label
		transactionPanneau.add(lblTransacSecuClient);//ajout du label au panneau transaction
		
		JTextField txtTransacSecuClient = new JTextField();//Création d'un JtextField 
		txtTransacSecuClient.setSize(200,30);//Définition de la taille du JtextField 
		txtTransacSecuClient.setLocation(0, 100);//Défini l'emplacement du JtextField 
		transactionPanneau.add(txtTransacSecuClient);//Ajoute le JtextField au panneau transaction
		
		
		
		JLabel lblTransacRefMedic = new JLabel("Référence médicament :");
		lblTransacRefMedic.setFont(lblTransacRefMedic.getFont().deriveFont(16.0f));
		lblTransacRefMedic.setSize(lblTransacRefMedic.getPreferredSize().width,lblTransacRefMedic.getPreferredSize().height);
		lblTransacRefMedic.setLocation(300, 65);
		transactionPanneau.add(lblTransacRefMedic);
		
		JTextField txtTransacRefMedic = new JTextField();
		txtTransacRefMedic.setSize(200,30);
		txtTransacRefMedic.setLocation(300,100);
		transactionPanneau.add(txtTransacRefMedic);
		
		
		
		JLabel lblTransacQuantMedic = new JLabel("Quantité désirée :");
		lblTransacQuantMedic.setFont(lblTransacQuantMedic.getFont().deriveFont(16.0f));
		lblTransacQuantMedic.setSize(lblTransacQuantMedic.getPreferredSize().width,lblTransacQuantMedic.getPreferredSize().height);
		lblTransacQuantMedic.setLocation(600, 65);
		transactionPanneau.add(lblTransacQuantMedic);
		
		JTextField txtTransacQuantMedic = new JTextField();
		txtTransacQuantMedic.setSize(200,30);
		txtTransacQuantMedic.setLocation(600,100);
		transactionPanneau.add(txtTransacQuantMedic);
		
		
		JLabel lblPrixTransacMedic = new JLabel("Prix du ou des médicaments achetées : ");
		lblPrixTransacMedic.setFont(lblPrixTransacMedic.getFont().deriveFont(16.0f));
		lblPrixTransacMedic.setSize(lblPrixTransacMedic.getPreferredSize().width,lblPrixTransacMedic.getPreferredSize().height);
		lblPrixTransacMedic.setLocation((800-lblPrixTransacMedic.getPreferredSize().width)/2, 150);
		transactionPanneau.add(lblPrixTransacMedic);
		
		
		
		/*-----------------------------------------------Panneau/onglet Médicament------------------------------------------------*/
		
		
		DefaultTableModel tableModelClient = new DefaultTableModel(tabClientsTitres,0);	
		JTable tableTransacClient = new JTable(tableModelClient);
		//tableTransacClient.setDefaultEditor(Object.class, null);//Empeche modification par l'utilisatuer du tableau affiché
		
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
				    	   
				    	   lblPrixTransacMedic.setText("Prix du ou des médicaments achetées : "+arrondi(Integer.parseInt(txtTransacQuantMedic.getText())*listeMedicaments.get(numMedic).getPrix(), 2));
				    	   lblPrixTransacMedic.setSize(lblPrixTransacMedic.getPreferredSize().width,lblPrixTransacMedic.getPreferredSize().height);
				    	   lblPrixTransacMedic.setLocation((800-lblPrixTransacMedic.getPreferredSize().width)/2, 150);

			    	   
			    	   if (listeMedicaments.get(numMedic).getQuantite()==0)
			    	   {
			    		   JOptionPane.showMessageDialog(this.medicamentPanneau,"Le stock du médicament est à présent de 0.","Stock vide",JOptionPane.INFORMATION_MESSAGE);
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
		
		

		
		
		
		/*-----------------------------------------------Panneau/onglet Médicament------------------------------------------------*/

		
		medicamentPanneau = new JPanel();//Création panneau medicament	
		
		medicamentPanneau.setLayout(null);
		//Création client
		JLabel labelCreationMedic = new JLabel("Création médicament :");
		labelCreationMedic.setFont(labelCreationMedic.getFont().deriveFont(16.0f));
		labelCreationMedic.setSize(labelCreationMedic.getPreferredSize().width,labelCreationMedic.getPreferredSize().height);
		labelCreationMedic.setLocation(10,10);
		medicamentPanneau.add(labelCreationMedic);
		
		
		JLabel lblMedicReference = new JLabel("Référence médicament :");
		lblMedicReference.setFont(lblMedicReference.getFont().deriveFont(12.0f));
		lblMedicReference.setSize(lblMedicReference.getPreferredSize().width,lblMedicReference.getPreferredSize().height);
		lblMedicReference.setLocation(10,40);
		medicamentPanneau.add(lblMedicReference);
		
		JTextField textFieldReference = new JTextField();
		textFieldReference.setSize(200,30);
		textFieldReference.setLocation(10,55);
		medicamentPanneau.add(textFieldReference);
		
		
		JLabel lblMedicLibelle = new JLabel("Libellé :");
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
		
		
		JButton boutonValiderMedicament = new JButton("Valider création médicament");
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
			           JOptionPane.showMessageDialog(this.medicamentPanneau,"Création du médicament faite.","Médicament créé",JOptionPane.INFORMATION_MESSAGE);
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

		
		
		//Approvisionner médicament
		
		JLabel labelApprovisionnerMedic = new JLabel("Approvisionner médicament :");
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
		
		
		JLabel lblApproMedicQuant = new JLabel("Quantité");
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
		    		   //Afficher message médoc non trouvé
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
		
		
		
		
		
		//Chercher médicament
		
		JLabel labelChercherMedic = new JLabel("Chercher médicament :");
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
		    		   jtaChercherMedic.append("Médicament non trouvé");
		    	   }
		    	   else
		    	   {			    			
		    		   jtaChercherMedic.setText("");
		    		   jtaChercherMedic.append("Réference : "+listeMedicaments.get(numMedic).getReference()+"\n");
		    		   jtaChercherMedic.append("Libellé : "+listeMedicaments.get(numMedic).getLibelle()+"\n");
		    		   jtaChercherMedic.append("Description : "+listeMedicaments.get(numMedic).getDescription()+"\n");
		    		   jtaChercherMedic.append("Quantité : "+listeMedicaments.get(numMedic).getQuantite()+"\n");
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
		
		
		JTextField inserer = new JTextField();//JTextField pour insérer les informations clientes
		inserer.setSize(100,20);
		inserer.setLocation(80, 100);
		
		//Création d'un JTextArea pour renseigner les informations à entre lors de la création/recherche client
		JTextArea Systm = new JTextArea();//JTeaxtArea est un JTesxtField sur plusieurs lignes
		Systm.setSize(300,200);
		Systm.setLocation(410,80);
		Systm.setEditable(false);
		Systm.setText("inserez le nom du nouveaux client");
		valider.addActionListener( new ActionListener() {		
			private Component clientPanneau;

			public void actionPerformed(ActionEvent e) {
		
			if(signal == true) //Signal défini si on crée ou cherche un client (si signal=true alors on crée un client sinon on cherche un client)
			{	
				parametre.add(creercliant(inserer));//On ajoute daans une arraylist les paramètres pour la création d'un client (rentré dans un text box)
				
				Systm.append("\n");
				x += 1;
				
				if(x==5) //x corerespond au nombre de fois qu'on appuit sur le bouton "valider", cette variable n'est utilisé que si le signal=true 
                {
					/*
					 * Dans cet condition, on vérifie que le numéro de sécu sociale n'est pas déjà utilisé par un autre client
					 */
					try 
					{
					verifNumSecu(parametre.get(4));
					
                    if(chercherNumClient(listeClients, parametre.get(4))==-1)
                    { 
                    	/*
                    	 * Dans le cas où le client n'existe pas alors ce dernier est ajouté à la liste des clients et le tableaux des clients est mis à jour
                    	 */
                    	listeClients.add(new Client(parametre.get(0),parametre.get(1),parametre.get(2),parametre.get(3),parametre.get(4)));
                    
                    	x = 0;
                    	tableModelClient.addRow(arrayEnTabClients(listeClients,listeClients.size()-1));

                    	for (int t=0;t<5;t++)
                    	{
                    		parametre.remove(0);
                    	}
                    	JOptionPane.showMessageDialog(this.clientPanneau,"Création du client faite.","Client créé",JOptionPane.INFORMATION_MESSAGE);
                    	Systm.setText("");
                    	
                    }
                    else
                    {
                    	/*
                    	 * Dans le cas où le client existe, le dernier paramètre à rentrer (le numéro de sécurité sociale) est supprimé, il faudra pour rajouter le client rerentrer le numéro de sécurité sociale
                    	 * cependant les autres paramètres ne seront pas à réécrire.
                    	 */
                    	Systm.append("\n"+"erreur, ce Numéro de sécurité existe déjà"+"\n");
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
				 * Dans le cas où l'on cherche le client. Via la fonction chercherNumClient, on recherhe l'indice de la liste du client correspondant 
				 * au numéro de sécurité donnée dans le JTextField.
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
					Systm.append("Numero de téléphone : "+listeClients.get(numClientRecherche).getNumeroTelephone()+"\n");
					Systm.append("Numéro de sécurité : "+listeClients.get(numClientRecherche).getNumSecuSociale()+"\n");
					
					
					tableModelMedicsClient.setRowCount(0);

					//Mise à jour du tableaux de la liste de l'historique des transactions du client
					for (int z=0;z<listeClients.get(numClientRecherche).getListeMedicamentClient().size();z++)
					{
						tableModelMedicsClient.addRow(arrayEnTabMedicaments(listeClients.get(numClientRecherche).getListeMedicamentClient(), z));
					}
				}
				
			}
			
			}
		});
		
		JButton change = new JButton();
		change.setText("Chercher client par son numéro de sécurité social");
		change.setLocation(200,80);
		change.setSize(200,200);
		change.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				/*
				 * L'appuis sur ce bouton permet le changement de mode entre création client et recherche d'un client.
				 * Lorsqu'on appuis sur le bouton les informations de créatio d'un client non fini sont remises à 0.
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
		
		JLabel lblPanneauClient = new JLabel("Création et recherche de clients :");
		lblPanneauClient.setFont(lblPanneauClient.getFont().deriveFont(30.0f));
		lblPanneauClient.setSize(lblPanneauClient.getPreferredSize().width,lblPanneauClient.getPreferredSize().height);
		lblPanneauClient.setLocation((800-lblPanneauClient.getPreferredSize().width)/2,10);
		clientPanneau.add(lblPanneauClient);
		
		JLabel lblhistoMedicsClient = new JLabel("Historique des achats du client recherché :");
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

		
		onglets = new JTabbedPane(SwingConstants.TOP);//Crée un objet de type JTabbedPane soit des onglets de panneau
		onglets.add("Transaction",transactionPanneau);//Ajout du panneau transactionPanneau à la liste d'onglets
		onglets.add("Medicament",medicamentPanneau);//Ajout du panneau medicamentPanneau à la liste d'onglets
		onglets.add("Client",clientPanneau);//Ajout du panneau clientPanneau à la liste d'onglets
		add(onglets);//Ajoute le JTabbedPane créé avec ses onglets à la page JFrame
		
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
			text ="chercher client par son numéro de sécurité social";
		
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
			j.setText("inserez le Numéro de sécurité rechercher");
		}
		else {
			a = true;
			j.setText("Insérez le Nom du nouveaux client");
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
		 * Fonction de conversion de la liste de clients envoyé en paramètre en Objet Tab
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
		 * Fonction de conversion de la liste de médicaments envoyé en paramètre en Objet Tab
		 */
        Object [] tabTempo = new Object[5];
            tabTempo[0]= LMedics.get(y).getReference();
            tabTempo[1]= LMedics.get(y).getLibelle();
            tabTempo[2]= LMedics.get(y).getDescription();
            tabTempo[3]= String.valueOf(LMedics.get(y).getQuantite());
            tabTempo[4]= String.valueOf(LMedics.get(y).getPrix());


        return tabTempo;
    }
	
	
	
	
	/*------------------------------------------------Fonctions Classe Client et Médicament------------------------------------------------------------*/
	
	
	
	public static void creerClient(ArrayList<Client> listeClients,String nom, String prenom, String adresse, String numeroTelephone,String numSecuSociale)
	{
		
		//Vérification des données à faire
		
		listeClients.add(new Client(nom, prenom, adresse, numeroTelephone,numSecuSociale));
	}
	
	public static void creerMedicament(ArrayList<Medicament> listeMedicaments,String reference, String libelle, String description, int quantite, double prix)
	{
		//Vérification des données à faire
		
		listeMedicaments.add(new Medicament(reference, libelle, description, quantite, arrondi(prix,2)));
		
		//Confirmation ajout avec messagebox
	}
	
	public static int approvisionnerMedicament(ArrayList<Medicament> listeMedicaments,String reference, int quantiteAjoutee)
	{
		/*
		 * Fonction d'approvisionnement d'un médicament
		 * 
		 * La fonction parcours la liste des médicaments reçu en paramètre et recherche le médicament
		 * avec la référence identique à celle envoyé en paramètre afin de pouvoir ajouter la quantité
		 * souhaité au stocke du médicament
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
		 * La fonction reçoit en entrée la liste de clients et de médicament ainsi que le numéro 
		 * de sécu du client et la référence du médicament voulu. La fonction parcours donc tous
		 * les cleints/médicaments à la recherche de ceux indiqués en paramètre pour faire l'achat
		 * du médicament d'un client et l'ajouter à son historique d'achat.
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
		 * Inclure gestion des erreurs avec les booléens.
		 */
		return numMedic;
	}
	
	static int chercherNumClient(ArrayList<Client> listeClients,String numSecuSociale)
	{
		/*
		 * Fonction de recherche d'un client
		 * 
		 * La fonction parcours la liste de clients envoyé en paramètre et cherche celui avec le même
		 * numéro de sécurité envoyé en paramètre afin de renvoyer l'indice du client de la liste des clients
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
		 * Fonction de recherche d'un médicament
		 * 
		 * La fonction parcours la liste de médicaments envoyé en paramètre et cherche celui avec la même
		 * numéro référence envoyée en paramètre afin de renvoyer l'indice du médicament de la liste des médicaments
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
		 * Fonction de vérification de l'existance d'un médicament
		 * 
		 * La fonction reçoit en paramètre la liste des médicaments et la réference d'un médicament
		 * souhaité être créé. La focntion parcours la liste des médicaments et vérifie si la référence 
		 * envoyé en paramètre n'existe pas déjà sur un autre médicament. Si la référence existe déjà alors
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
		 * Fonction appel exception de valeur négative ou nulle
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
		 * Fonction appel exception numéro de sécurité sociale inférieru à 13 caractères
		 */
		if (numSecu.length()<13)
		{
			throw new ExceptionNumSecuNonValide();
		}
	}
	
	/*-------------------------------------------(Fonctions supplémentaires)------------------------------------------------------------*/
	
	static double arrondi(double valeur, int arrondi) 
	{
		return (double) ( (int) (valeur * Math.pow(10, arrondi) + .5)) / Math.pow(10, arrondi);
	}
}

