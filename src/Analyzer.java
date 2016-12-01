


/**
 * Clase que realiza el análisis de las actualizaciones
 * de las trazas.
 * @author Andres
 *
 */
public class Analyzer
{
	AnalyzerWatcher m_objWatcher;
	String m_strPathDef = "D:\\Java Projects\\AutomaticTestSan\\DirectorytoWatch";
	
	public Analyzer()
	{
		m_objWatcher = new AnalyzerWatcher();
	}
	
	public Analyzer(String strPath)
	{
		m_objWatcher = new AnalyzerWatcher(strPath);
	}

	public void Initialize()
	{
		// Inicializacion del servicio de vigilancia del directorio
		m_objWatcher.Initialize();
	}
	
	public void Start()
	{
		// Inicio de la vigilancia.
		m_objWatcher.Start();
	}
}
