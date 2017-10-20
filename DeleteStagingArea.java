import java.io.File;
import java.io.FileNotFoundException;
public class DeleteStagingArea
{
	private static File file;
	private static File directory;
	public static final String stage="C:\\Users\\abhindes\\AppData\\Roaming\\JDeveloper\\system11.1.1.7.40.64.93";
	public static final String oj2dee="o.j2ee\\drs";
	public static final String servers="DefaultDomain\\servers\\DefaultServer\\tmp";
	public static void main(String [] args)
	{
		try
		{	
			Runnable runner1=new Thread()
			{
				public void run()
				{
					try
					{
						filesPresent(servers);
					}
					catch(FileNotFoundException io)
					{
						io.printStackTrace();
			
					}
				}
			};
			Runnable runner2=new Thread()
			{
				public void run()
				{
					try
					{
						filesPresent(oj2dee);
					}
					catch(FileNotFoundException io)
					{
						io.printStackTrace();
			
					}
				}
			};
			((Thread)runner1).start();
			((Thread)runner1).join();
			((Thread)runner2).start();
			((Thread)runner2).join();
			//System.out.println(filesPresent("DefaultDomain\\servers\\DefaultServer\\tmp"));
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	public static void filesPresent(String dir) throws FileNotFoundException
	{
		//boolean status=false;
		directory=new File(stage+File.separator+dir);
		//System.out.println(directory.getAbsolutePath());
		if(directory.exists())
		{
			deleteFiles(directory);
		}
		
		
	}
	public static void deleteFiles(File files) throws FileNotFoundException
	{
		System.out.println(Thread.currentThread().getName());
		//System.out.println(dir);
		String [] list=null;
		if(files.isDirectory())
		{
			if(files.list().length==0 && (!(files.getName().contains("tmp") || files.getName().contains("drs"))))
			{
				files.delete();
			}
			else
			{
				list=files.list();
				for(String str : list)
				{
					file=new File(files,str);
					deleteFiles(file);
				}
				if(files.list().length==0 && (!(files.getName().contains("tmp") || files.getName().contains("drs"))))
				{
					files.delete();
				}
			}
		}
		else
		{
			files.delete();
		}
	}
}