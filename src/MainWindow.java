
import javax.swing.*;

import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame implements ActionListener
{
	/*******************************************************************************************************************************/
	/******************************							CONSTANTS								   *****************************/
	/*******************************************************************************************************************************/		
	static
	{
		try 
		{
			for (javax.swing.UIManager.LookAndFeelInfo info:
				javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Windows".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
	/*******************************************************************************************************************************/
	/******************************							VARIABLES								   *****************************/
	/*******************************************************************************************************************************/
	//***************************************************************************** //
	// 						COMPONENTES DE LA VENTANA								//
	//***************************************************************************** //
	JMenuBar 		m_objMenuBar;
	JMenu    		m_objMenuFile;
	JMenuItem 		m_objMenuItemFile;
	JFileChooser 	m_objFileChooser;
	//***************************************************************************** //
	
	//***************************************************************************** //
	// 						ELEMENTOS FICHERO XML									//
	//***************************************************************************** //
	 Element m_objXmlRootNode;
	
	ArrayList<Test>	m_aobjTestList;			// Lista de test a realizar.
	
	/*******************************************************************************************************************************/
	/******************************							METODOS 								   *****************************/
	/*******************************************************************************************************************************/		
	public MainWindow()
	{
		// configuracion de la ventana
		ConfigurationWindow();
		
		// Inicialización de los componentes.
		InitializeComponents();
		
		// Lista de test
		m_aobjTestList = new ArrayList();
	}
	public void ConfigurationWindow()
	{
		this.setTitle("Automatic Test Application");
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void InitializeComponents()
	{
		// Barra de menu de la ventana principal
		m_objMenuBar = new JMenuBar();
		
		// Menu Archivo
		m_objMenuFile 		= new JMenu("File");
		m_objMenuItemFile 	= new JMenuItem("Open");
		m_objMenuItemFile.addActionListener(this);
		m_objMenuFile.add(m_objMenuItemFile);		
		m_objMenuBar.add(m_objMenuFile);
		
		// Se asigna el menu a la ventana.
		this.setJMenuBar(m_objMenuBar);
		
		m_objFileChooser = new JFileChooser();
	}
	
	public boolean Start()
	{
		boolean fReturn = true;
		return fReturn;
	}
	
	public boolean Stop()
	{
		boolean fReturn = true;
		return fReturn;
	}
	
	public boolean LoadXml(String strFilePath)
	{
		boolean fReturn = true;							// Variable del estado de la carga
	    SAXBuilder builder = new SAXBuilder();			//Se crea un SAXBuilder para poder parsear el archivo
	    Document document;	   	   
	    File xmlFile = new File(strFilePath);
	    
	    try
	    {
	        //Se crea el documento a traves del archivo
	        document = (Document) builder.build( xmlFile );
	        
	        //Se obtiene la raiz 'tests'
	        m_objXmlRootNode = document.getRootElement();
	 	        
	    }catch ( IOException io ) {
	        System.out.println( io.getMessage() );
	    }catch ( JDOMException jdomex ) {
	        System.out.println( jdomex.getMessage() );
	    }	
		return fReturn;
	}
	
	public ArrayList<Test> GetTestFromXml (String strFilePath)
	{
		ArrayList<Test> aobjTestList = new ArrayList<Test>();
		Test 			objTest;
		List 			aobjList;
	    Element 		objElementTest;
	    List 			aobjFieldList;	    
	    String  		strTestName;
	    
		//Se obtiene la lista de hijos de la raiz 'tests'
	    aobjList = m_objXmlRootNode.getChildren();
 
        //Se recorre la lista de hijos de 'testList'
        for ( int i = 0; i < aobjList.size(); i++ )
        {
            //Se obtiene el elemento 'test'
            objElementTest = (Element) aobjList.get(i);
 
            //Se obtiene el atributo 'nombre' que esta en el tag 'test'
            strTestName = objElementTest.getAttributeValue("name");
 
            System.out.println( "Test: " + strTestName );
            
            // Se crea el objeto Test que se almacenará en una lista.
            objTest = new Test(strTestName, strFilePath);
 
            //Se obtiene la lista de hijos del tag 'test'
            aobjFieldList = objElementTest.getChildren();
            
            // Carga de la configuracion del test.
            objTest.LoadConfiguration(aobjFieldList);
            
            // Se añade el test a la lista
            aobjTestList.add(objTest);
        }
        return aobjTestList;
	}
	
	
	 public void actionPerformed(ActionEvent e) 
	 {
	    Container objContainer = this.getContentPane();
	    File objFile;
	    int returnVal;
	    try
	    {
	    	// Apertura del fichero con la configuracion de los test a leer.	    
	    	if (e.getSource() == m_objMenuItemFile) 
	    	{
	    		returnVal = m_objFileChooser.showOpenDialog(this);
	    		if (returnVal == JFileChooser.APPROVE_OPTION)
	    		{
	    			// Se obtiene el ficehro con la configuracion
	    			objFile = m_objFileChooser.getSelectedFile();	    			
	    			
	    			// Lectura del fichero Xml con los test    			
	    			LoadXml(objFile.getAbsolutePath());
	    			
	    			// Obtiene los test del fichero xml.
	    			m_aobjTestList = GetTestFromXml(objFile.getAbsolutePath());
	    			
	    			// Muestra la informacion leida de los test
	    			PrintInfo();
	    		}
	    	}
	    }
	    catch (Exception ex)
	    {
	    	System.out.println(ex);
	    }
	 }	
	 
	 public void PrintInfo()
	{
		System.out.println("**************************");		
		for (int nTest = 0; nTest < m_aobjTestList.size(); nTest++)
		{
			m_aobjTestList.get(nTest).PrintInfo();
		}
		System.out.println("**************************");
		System.out.println("**************************");
	}
	 
}
