package GetLZ;

import java.util.Vector;

import AssistStaff.Config;
import AssistStaff.Mission;
import Main.MissionPanel;

import com.itextpdf.text.Document;

public class GetLZ extends Thread{
	private Vector<String> vDownLoad = new Vector<String>();//URL�б�
	private Vector<String> vFileList = new Vector<String>();//���غ�ı����ļ����б�
	private String title = null;
	private String author = null;
	private String link = null;
	private int mark = 0;
	private int title_mark = 0;
	private int pages = 1;
	private int bug =0;
	private Document doc = null;
	private boolean stop_mark =false;
	private boolean downpdf;
	
	public void StopProcess(){
		if (downpdf){
			doc.close();
		}
		title = null;
		author = null;
		link = null;
		mark = 0;
		title_mark = 0;
		bug =0;
		pages = 1;
		vDownLoad = new Vector<String>();
		vFileList = new Vector<String>();
		stop_mark = true;
	}
}
