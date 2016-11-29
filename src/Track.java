import java.util.ArrayList;

/**
 * 
 */

/**
 * @author asantoyoa
 *
 */
public class Track 
{
	/*******************************************************************************************************************************/
	/******************************							CONSTANTS								   *****************************/
	/*******************************************************************************************************************************/
	public enum FequencyTypes
	{
		No_defined,
		Fixed,
		Switch,
		Stagger,
		Complex
	};
	
	public enum PriTypes
	{
		No_defined,
		Fixed,
		Switch,
		Stagger,
		Complex
	};
	
	public enum PwTypes
	{
		No_defined,
		Fixed,
		Switch,
		Stagger,
		Complex
	};
	
	public enum ArpTypes
	{
		No_defined,
		Lockon,		
		Circular,
		Sectorial,
		Raster,
		Conical,
		Sprial
	};
	
	/*******************************************************************************************************************************/
	/******************************							VARIABLES								   *****************************/
	/*******************************************************************************************************************************/
	int						m_nTrackNumber;
	ArrayList<Parameter> 	m_aobjFrequencies;
	ArrayList<Parameter> 	m_aobjPris;
	ArrayList<Parameter> 	m_aobjPws;
	
	/*Parameter 		m_objFrequency;
	Parameter 		m_objPri;
	Parameter 		m_objPw;*/
	Parameter 				m_objPa;
	FequencyTypes   m_objFrecType;
	PriTypes   		m_objPriType;
	PwTypes   		m_objPwType;
	ArpTypes   		m_objArpType;
	
	// Parametros de caracterización
	// Tipo de frecuencia
	// Tipo de pri
	// tipo de pw
	// tipo de arp
		
	/**
	 * Constructor
	 */
	public Track()
	{
		/*m_objFrequency 	= new Parameter();
		m_objPri 		= new Parameter();
		m_objPw 		= new Parameter();*/
		m_aobjFrequencies 	= new ArrayList();
		m_aobjPris		  	= new ArrayList();
		m_aobjPws		  	= new ArrayList();		
		m_objPa 			= new Parameter();
		m_objFrecType   	= FequencyTypes.No_defined;
		m_objPriType    	= PriTypes.No_defined;
		m_objPwType     	= PwTypes.No_defined;
		m_objArpType    	= ArpTypes.No_defined;
		
	}
	
	/**
	 * Constructor
	 * @param objFrec
	 * @param objPri
	 * @param objPw
	 * @param objPa
	 * @param objFrecType
	 * @param objPriType
	 * @param objPwType
	 * @param objArpType
	 */
	public Track(Parameter objFrec, 
				 Parameter objPri, 
				 Parameter objPw, 
				 Parameter objPa, 
				 FequencyTypes objFrecType, 
				 PriTypes objPriType, 
				 PwTypes objPwType,
				 ArpTypes objArpType)
	{
		/*m_objFrequency 	= objFrec;
		m_objPri 		= objPri;
		m_objPw 		= objPw;*/
		
		m_aobjFrequencies.add(objFrec);
		m_aobjPris.add(objPri);
		m_aobjPws.add(objPw);
		m_objPa 		= objPa;
		m_objFrecType   = objFrecType;
		m_objPriType    = objPriType;
		m_objPwType     = objPwType;
		m_objArpType    = objArpType;
		
	}
	
	public void AddParameter(String strParameterType, Parameter objParam)
	{
		if (strParameterType.toUpperCase().equals("Frequency".toUpperCase()))
		{
			m_aobjFrequencies.add(objParam);
		}
		else if (strParameterType.toUpperCase().equals("Pri".toUpperCase()))
		{			
			m_aobjPris.add(objParam);
		}
		else if (strParameterType.toUpperCase().equals("Pw".toUpperCase()))
		{			
			m_aobjPws.add(objParam);
		}
	}
	
	public Parameter GetParameter(String strParameterType, int nParameterPos)
	{
		Parameter objParamReturn = null;
		
		if (strParameterType.toUpperCase().equals("Frequency".toUpperCase()))
		{
			objParamReturn = m_aobjFrequencies.get(nParameterPos);
		}
		else if (strParameterType.toUpperCase().equals("Pri".toUpperCase()))
		{			
			objParamReturn = m_aobjPris.get(nParameterPos);
		}
		else if (strParameterType.toUpperCase().equals("Pw".toUpperCase()))
		{			
			objParamReturn = m_aobjPws.get(nParameterPos);
		}		
		return objParamReturn;
	}
}
