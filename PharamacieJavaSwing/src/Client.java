import java.util.ArrayList;

public class Client {

	private String m_numSecuSociale;
	private String m_nom;
	private String m_prenom;
	private String m_adresse;
	private String m_numeroTelephone;
	private ArrayList<Medicament> m_listeMedicamentClient = new ArrayList<>();
	
	public Client(String nom, String prenom, String adresse, String numeroTelephone,String numSecuSociale)
	{
		m_numSecuSociale=numSecuSociale;
		m_nom=nom;
		m_prenom=prenom;
		m_adresse=adresse;
		m_numeroTelephone=numeroTelephone;
	}
	
	public void acheterMedicaments(Medicament medicAchetee, int quantiteDesiree)
	{
		double prixAchat=quantiteDesiree*medicAchetee.getPrix();	

		medicAchetee.reduireStock(quantiteDesiree);

		m_listeMedicamentClient.add(new Medicament(medicAchetee.getReference(), medicAchetee.getLibelle(), medicAchetee.getDescription(), quantiteDesiree, medicAchetee.getPrix()));	
	}
	
	/*-------------------------------------------(Accesseurs)------------------------------------------------------------*/
	
	public String getNumSecuSociale()
	{
		return m_numSecuSociale;
	}
	
	public String getNom()
	{
		return m_nom;
	}
	
	public String getPrenom()
	{
		return m_prenom;
	}
	
	public String getAdresse()
	{
		return m_adresse;
	}
	
	public String getNumeroTelephone()
	{
		return m_numeroTelephone;
	}
	
	public ArrayList<Medicament>  getListeMedicamentClient()
	{
		return m_listeMedicamentClient;
	}
	
}
