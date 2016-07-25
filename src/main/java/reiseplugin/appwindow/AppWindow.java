package appwindow;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class AppWindow extends ApplicationWindow{

	public AppWindow(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Control createContents(Composite parent) {
		getShell().setSize(500,500);
		return super.createContents(parent);
	}


}
