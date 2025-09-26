package project.usercompute;

import project.datacompute.DataComputeAPI;

public class UserComputeAPIimpl implements UserComputeAPI{

	private project.intercompute.InterComputeAPI inter;
	public void setInter( project.intercompute.InterComputeAPI inter ) { this.inter = inter; }
	public void handleRequest(UserRequest req) {
		// TODO Auto-generated method stub
		
	}
}
