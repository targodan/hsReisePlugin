package reiseplugin.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AppWindow {

	private Display display;
	private Shell shell;

	public AppWindow() {
		this.shell = new Shell(Display.getDefault());
		display = Display.getDefault();
		shell = new Shell(display);
		createContents(shell);
	}

	private void createContents(Shell shell) {
		shell.setLayout(new FillLayout());
		shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL));
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL));

		Label test = new Label(composite, SWT.NONE);
		test.setText("test");
	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public static void main(String[] args) {
		AppWindow w = new AppWindow();
		w.open();
	}

}
