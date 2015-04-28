



import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.WorkItemClient;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.query.WorkItemCollection;
import com.microsoft.tfs.core.httpclient.URI;
import com.microsoft.tfs.sdk.samples.console.ConsoleSettings;

public class TFS {

    

	
    public static void main(String[] args) 
    { 
    	//URI serverURI=new URI("http://rl-tfsapp01:8080/tfs/DefaultCollection",false,"UTF-8");
    	TFSTeamProjectCollection tpc=ConsoleSettings.connectToTFS();
    	
        //TFSTeamProjectCollection tpc =  new TFSTeamProjectCollection("http://rl-tfsapp01:8080/tfs/DefaultCollection");
        
        Project project = tpc.getWorkItemClient().getProjects().get("Beaufort");  
        WorkItemClient workItemClient = tpc.getWorkItemClient(); 
        
        // Define the WIQL query.          
        String wiqlQuery = 
            "Select ID, Title from WorkItems where (State = 'Active') order by Title"; 
        
        // Run the query and get the results.          
        WorkItemCollection workItems = workItemClient.query(wiqlQuery);

        System.out.println("Found " + workItems.size() + " work items."); 
        System.out.println(); 
        
        // Write out the heading.          
        System.out.println("ID\tTitle"); 
        
        // Output the first 20 results of the query 
        final int maxToPrint = 20; 
        
        for (int i = 0; i < workItems.size(); i++)          
        { 
            if (i >= maxToPrint)              
            { 
                System.out.println("[...]");                  
                break; 
            } 
            WorkItem workItem = workItems.getWorkItem(i); 
            
            System.out.println(workItem.getID() + "\t" + workItem.getTitle());  
        } 
        
        System.out.println("Done"); 
    
    }

}