import javax.swing.JOptionPane;

import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod;


public class TravelRecommender implements StandardCBRApplication{


	public void configure() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public void postCycle() throws ExecutionException {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args){
		
		//Lanzar el SGDB
		jcolibri.test.database.HSQLDBserver.init();
		
		/*	En HSQLDBserver. init habria que configurar el nombre que tendra nuestra base de datos, y
		 * 	el fichero del cual leemos los datos necesarios para rellenar la BD. En nuestro caso de las quinielas, 
		 * 	usaremos varios para cargarlos en ¿una BD o más?
		 * */
		
		// Crear el objeto que implementa la aplicación CBR
		TravelRecommender trApp = new TravelRecommender();
		try{
			
			//Configuracion
			trApp.configure();
			//Preciclo
			trApp.preCycle();
			
			//Crear objeto que almacena la consulta
			CBRQuery query = new CBRQuery();
			query.setDescription(new TravelDescription());
			
			do{
				// Obtener los valores de la consulta
				ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query, null,	null);
				
				/*	El método obtainQueryWithoutInitialValues que muestra una ventana que permite rellenar los distintos
					atributos de la consulta. Este método permite configurar los atributos a mostrar y las etiquetas a
					utilizar para cada uno. También existe otro método de la misma clase que permite establecer valores
					predeterminados que serán mostrados al usuario.
				 * */				
				
				// Ejecutar el ciclo
				trApp.cycle(query);
				
			}while(JOptionPane.showConfirmDialog(null, "Continuar?")== JOptionPane.OK_OPTION);
			
		}
		catch(Exception e){
			org.apache.commons.logging.LogFactory.getLog(TravelRecommender.class).error(e);
		}
		
		//Apagar el SGDB
		jcolibri.test.database.HSQLDBserver.shutDown();
	}
	
	
}
