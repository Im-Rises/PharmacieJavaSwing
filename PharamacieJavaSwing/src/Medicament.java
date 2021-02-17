
public class Medicament {

	private String m_reference;
	private String m_libelle;
	private String m_description;
	private int m_quantite;
	private double m_prix;
	
	public Medicament(String reference, String libelle, String description, int quantite, double prix)
	{
		m_reference=reference;
		m_libelle=libelle;
		m_description=description;
		m_quantite=quantite;
		m_prix=prix;
	}
	
	/*-------------------------------------------(Méthodes)------------------------------------------------------------*/
	
	public void approvisionnerStock(int quantiteAjoutee) 
	{
		m_quantite +=quantiteAjoutee;
	}
	
	public void reduireStock(int quantiteAchetee)
	{
		m_quantite-=quantiteAchetee;
	}

	/*-------------------------------------------(Accesseurs)------------------------------------------------------------*/
	
	public String getReference()
	{
		return m_reference;
	}
	
	public String getLibelle()
	{
		return m_libelle;
	}
	
	public String getDescription()
	{
		return m_description;
	}
	
	public int getQuantite()
	{
		return m_quantite;
	}
	
	public double getPrix()
	{
		return m_prix;
	}
	
}
