import java.util.ArrayList;

public class Configuration {
	/*******************************************************************************************************************************/
	/******************************							CONSTANTS								   *****************************/
	/*******************************************************************************************************************************/
	
	/*******************************************************************************************************************************/
	/******************************							VARIABLES								   *****************************/
	/*******************************************************************************************************************************/
	ArrayList<Track> m_aobjTracks; // Array de trazas a analizar en el test
	
	/**
	 * Constructor
	 */
	public Configuration()
	{
		m_aobjTracks = new ArrayList<Track>();
	}

	public void AddTrack(Track objTrack)
	{
		m_aobjTracks.add(objTrack);
	}
	
	public void PrintInfo()
	{
		for (int nTrack = 0; nTrack < m_aobjTracks.size(); nTrack++)
		{
			System.out.println("**************************");
			System.out.println("***Track_id: " + Integer.toString(m_aobjTracks.get(nTrack).m_nTrackNumber) + " ***");
			for (int nFrec = 0; nFrec < m_aobjTracks.get(nTrack).m_aobjFrequencies.size(); nFrec++)
			{
				System.out.println("***Frecuencia(Max,Min): (" + Integer.toString(m_aobjTracks.get(nTrack).m_aobjFrequencies.get(nFrec).m_nMaximum) + 
					             ", " + Integer.toString(m_aobjTracks.get(nTrack).m_aobjFrequencies.get(nFrec).m_nMinimum) + ") ***");
			}
			for (int nPri = 0; nPri < m_aobjTracks.get(nTrack).m_aobjPris.size(); nPri++)
			{
				System.out.println("***Pri(Max,Min): (" + Integer.toString(m_aobjTracks.get(nTrack).m_aobjPris.get(nPri).m_nMaximum) + 
					             ", " + Integer.toString(m_aobjTracks.get(nTrack).m_aobjPris.get(nPri).m_nMinimum) + ") ***");
			}
			for (int nPw = 0; nPw < m_aobjTracks.get(nTrack).m_aobjPris.size(); nPw++)
			{
				System.out.println("***Pw(Max,Min): (" + Integer.toString(m_aobjTracks.get(nTrack).m_aobjPws.get(nPw).m_nMaximum) + 
					             ", " + Integer.toString(m_aobjTracks.get(nTrack).m_aobjPws.get(nPw).m_nMinimum) + ") ***");
			}
			System.out.println("**************************");
		}
	}
}
