package org.latexlab.docs.client.content.menus;

import org.latexlab.docs.client.commands.SystemPasteCommand;
import org.latexlab.docs.client.content.icons.Icons;
import org.latexlab.docs.client.events.HasCommandHandlers;
import org.latexlab.docs.client.widgets.DynamicMenuBar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Contains a Formulas menu with on-demand loading.
 */
public class DynamicFormulasMenu extends DynamicMenuBar {

  protected static DynamicFormulasMenu instance;
	
  /**
   * Retrieves the single instance of this class.
   * 
   * @param commandSource the command source.
   */
  public static DynamicFormulasMenu get(HasCommandHandlers commandSource) {
    if (instance == null) {
      instance = new DynamicFormulasMenu(commandSource);
    }
    return instance;
  }
  
  /**
   * Constructs a formulas menu.
   * 
   * @param commandSource the command source
   */
  protected DynamicFormulasMenu(HasCommandHandlers commandSource) {
    super(true, commandSource);
  }

  /**
   * Asynchronously loads the MenuBar's sub items.
   * 
   * @param callback the callback carrying the sub items
   */
  @Override
  protected void getSubMenu(final AsyncCallback<ExtendedMenuItem[]> callback) {
    GWT.runAsync(new RunAsyncCallback() {
		@Override
		public void onFailure(Throwable reason) {
	      callback.onFailure(reason);
		}
		@Override
		public void onSuccess() {
		  callback.onSuccess(new ExtendedMenuItem[] {
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Embedded Formula", new SystemPasteCommand("$$")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Formula", new SystemPasteCommand("\\[\n\\]")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Enumerated Equation", new SystemPasteCommand("\\begin{equation}\n  \\end{equation}")),
			new ExtendedMenuItem(Icons.editorIcons.Blank(), "Equation Array", new SystemPasteCommand("\\begin{align}\n  \\end{align}"))
		  });
		}
    });
  }

}