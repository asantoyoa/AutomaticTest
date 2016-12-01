import java.io.IOException;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;


/**
 * @author Andres
 *
 */
public class AnalyzerWatcher
{
	Path 			m_objFolder;	
	WatchService	m_objWatchService;	// Create a new Watch Service
	
	/**
	 * Constructor
	 */
	public AnalyzerWatcher()
	{
		m_objFolder = Paths.get(System.getProperty("user.home"));
		 				
	}
	
	/**
	 * Constructor
	 */
	public AnalyzerWatcher(String strPath)
	{
		m_objFolder = Paths.get(strPath);
		 				
	}
	
	public void Initialize()
	{
		try 
		{
			m_objWatchService = FileSystems.getDefault().newWatchService();
			 
			// Register events
			m_objFolder.register(m_objWatchService, StandardWatchEventKinds.ENTRY_CREATE,
													StandardWatchEventKinds.ENTRY_MODIFY,
													StandardWatchEventKinds.ENTRY_DELETE);			
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Start()
	{
		// Añadir un bucle que se ejecute todo el tiempo o un hilo.
		// Start the infinite polling loop
		WatchKey objKey 	= null;
		Kind<?>  objKind 	= null;		
		try
		{				
			while(true) 
			{			
				//Espera activa a un evento.
				objKey = m_objWatchService.take();
						
				// Dequeueing events
				objKind = null;
				for(WatchEvent<?> objWatchEvent : objKey.pollEvents()) 
				{
					// Get the type of the event
					objKind = objWatchEvent.kind();
					if (OVERFLOW == objKind) 
					{
						continue; //loop
					} 
					else if (ENTRY_CREATE == objKind) 
					{
						// A new Path was created 
						@SuppressWarnings("unchecked")
						Path objNewPath = ((WatchEvent<Path>) objWatchEvent).context();						
						System.out.println("New path created: " + objNewPath);
					}
					// Modificacion
					else if (ENTRY_MODIFY == objKind) 
					{
						
					}
					
				}			
				if(!objKey.reset()) 
				{
					break; //loop
				}
			}
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Stop()
	{
		// Closes a watch service
		try {
			m_objWatchService.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
