import java.io.File;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;
import org.apache.commons.io.input.TailerListenerAdapter;


public class TailerFile
{
	private static final int SLEEP = 500;
	Tailer 			m_objTailer 	= null;
	TailerListener 	m_objMyListener = null;
				
	public TailerFile(String strFileName) 
	{
		//m_objMyListener = new TailerListener();		
		m_objTailer = Tailer.create(new File(strFileName), m_objMyListener, SLEEP);
	}

	

	
}
