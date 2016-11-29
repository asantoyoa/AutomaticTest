/**
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Element;

/**
 * @author asantoyoa
 *
 * Definition of Test Class
 */
public class Test 
{
	/*******************************************************************************************************************************/
	/******************************							CONSTANTS								   *****************************/
	/*******************************************************************************************************************************/
	
	/*******************************************************************************************************************************/
	/******************************							VARIABLES								   *****************************/
	/*******************************************************************************************************************************/
	Boolean 		m_FinalResult = false;		// Resultado del test (false  -> Fail ; true  -> Pass).
	String  		m_strFileName = "";			// Nombre del fichero de configuracion.
	String  		m_strTestName = "";			// Nombre del test
	Configuration 	m_objConfiguration;			// Configuracion del test.
	
	/**
	 * Constructor
	 */
	public Test()
	{
		m_objConfiguration = new Configuration();
	}
		
	
	/**
	 * Constructor
	 */
	public Test (String strTestName, String strFileName)
	{
		m_strTestName 		= strTestName;
		m_strFileName 		= strFileName;
		m_objConfiguration 	= new Configuration();
	}
	
	/*******************************************************************************************************************************/
	/******************************							METODOS 								   *****************************/
	/*******************************************************************************************************************************/
	
	/**
	 * Lee la configuracion del test
	 *  
	 */
	public void LoadConfiguration(List aobjFieldList)
	{
		Element 		objTrackField, objParameterField;
	    String 			strName = "", strParamName = "";
	    String 			strType = "";
	    String 			strValue = "";
	    String			strMax, strMin = "";
	    Track			objTrack;
	    Parameter		objParameter;
	    List			aobjParameterList;
	    String[]        astrParamValues;
	    
		//Se recorre la lista de campos
        for ( int nField = 0; nField < aobjFieldList.size(); nField++ )
        {
            //Se obtiene el elemento 'trk_xx'
        	objTrackField = (Element)aobjFieldList.get(nField);
     
        	System.out.println( "\t Traza: " + objTrackField.getAttributeValue("name"));

        	// Nuevo objeto traza
        	objTrack = new Track();
        	
        	// Se obtienen los parametros de la traza
        	aobjParameterList = objTrackField.getChildren();
        	
        	// Se recorren los parametros de la traza
        	for ( int nParemeter = 0; nParemeter < aobjParameterList.size(); nParemeter++ )
            {
        		//Se obtiene el parametro de la traza
        		objParameterField = (Element)aobjParameterList.get(nParemeter);
            	
        		// Inicializacion del parametro
        		objParameter = new Parameter();
        		
        		// Se obtiene le nombre del parámetro
                strName = objParameterField.getChildTextTrim("name");
                strParamName = objParameterField.getName();
                
                if ( strParamName.toUpperCase().equals("Frequency".toUpperCase()) )
                {
                	/*
                	// Se obtienen los valores maximo y minimo delimitados por ;
                	astrParamValues = strValue.split(";");                	
                	objParameter.setMinimum(Integer.parseInt(astrParamValues[0]));
                	objParameter.setMaximum(Integer.parseInt(astrParamValues[1]));*/
                	                                    
                	// Se obtiene el valor maximo del parametro
                    strMax = objParameterField.getChildTextTrim("max");
                    // Se obtiene el valor minimo del parametro
                    strMin = objParameterField.getChildTextTrim("min");
                    
                    // Se asignan los valores maximos y minimos
                    objParameter.setMinimum(Integer.parseInt(strMin));
                	objParameter.setMaximum(Integer.parseInt(strMax));
                	
                	// Calculo del valor medio
                	objParameter.CaclulateAvg();
                }
                else if ( strParamName.toUpperCase().equals("Pri".toUpperCase()) )
                {                	
                	// Se obtiene el valor maximo del parametro
                    strMax = objParameterField.getChildTextTrim("max");
                    // Se obtiene el valor minimo del parametro
                    strMin = objParameterField.getChildTextTrim("min");
                    
                    // Se asignan los valores maximos y minimos
                    objParameter.setMinimum(Integer.parseInt(strMin));
                	objParameter.setMaximum(Integer.parseInt(strMax));
                	
                	// Calculo del valor medio
                	objParameter.CaclulateAvg();
                }
                // Se añade el parametro a la lista de parametros de la traza
                objTrack.AddParameter(strParamName, objParameter);
            }
        	// Se añade la traza a la lista de trazas del test
        	m_objConfiguration.AddTrack(objTrack);
            System.out.println( "\t" + strName + "\t\t" + strValue);
        }
	}
	
	public void PrintInfo()
	{
		System.out.println("**************************");
		System.out.println("***Fichero xml test: " + m_strFileName + " ***");
		System.out.println("***Test: " + m_strTestName + " ***");
		m_objConfiguration.PrintInfo();
		System.out.println("**************************");
		System.out.println("**************************");
	}
}
