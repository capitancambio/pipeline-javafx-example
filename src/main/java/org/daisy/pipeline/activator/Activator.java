package org.daisy.pipeline.activator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import javafx.application.Application;

public class Activator extends Application implements BundleActivator 
{

    Stage stage;

    public void start(final Stage stage) {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                Activator.this.stage = stage;
                BorderPane pane = new BorderPane();
                Scene scene = new Scene(pane, 400, 200);
                pane.setCenter(new Label("This is a JavaFX Scene in a Stage"));
                stage.setScene(scene);
                stage.show();
            }
        });
    }
    @Override
    public void start(BundleContext context) throws Exception
    {
            //Otherwise launch will block
            new Thread(){
                    public void run(){
                            javafx.application.Application.launch(Activator.class);
                    }
            }.start();
            System.out.println("Main Module is loaded!");
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                stage.close();
            }
        });
        System.out.println("Main Module is unloaded!");
    }
}
