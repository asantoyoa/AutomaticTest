/**
 * 
 */

/**
 * @author asantoyoa
 *
 */
public class Parameter 
{
	/*******************************************************************************************************************************/
	/******************************							CONSTANTS								   *****************************/
	/*******************************************************************************************************************************/
	
	/*******************************************************************************************************************************/
	/******************************							VARIABLES								   *****************************/
	/*******************************************************************************************************************************/
	int 	m_nMaximum 	= 0; 			// Valor maximo del parametro
	int 	m_nMinimum 	= 0; 			// Valor minimo del parametro
	int 	m_nAvg 		= 0; 			// Valor media del parametro
	String 	m_strParameterType = ""; 	// Tipo del parametro
	
	/**
	 * Constructor
	 */
	public Parameter()
	{
		
	}

	public Parameter (int nMaximum, int nMinimum, int nAvg)
	{
		setAvg(nAvg);
		setMaximum(nMaximum);
		setMinimum(nMinimum);
	}
	
	public int getM_nMaximum() 
	{
		return m_nMaximum;
	}

	public void setMaximum(int m_nMaximum) 
	{
		this.m_nMaximum = m_nMaximum;
	}

	public int getMinimum() 
	{
		return m_nMinimum;
	}

	public void setMinimum(int m_nMinimum) 
	{
		this.m_nMinimum = m_nMinimum;
	}

	public int getAvg() 
	{
		return m_nAvg;
	}

	public void setAvg(int m_nAvg) 
	{
		this.m_nAvg = m_nAvg;
	}
	
	/*******************************************************************************************************************************/
	/******************************							METODOS 								   *****************************/
	/*******************************************************************************************************************************/
	public void CaclulateAvg() 
	{
		setAvg((this.m_nMaximum + this.m_nMinimum) / 2);
	}
	
}
