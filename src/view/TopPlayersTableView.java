//Gal Ashkenazi 315566752 - Final Test
package view;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 * View TOP 10 players table 
 * @author GalAs
 *
 */
public class TopPlayersTableView {
	private ArrayList<model.TopPlayers> listTopPlayers;
	
	/**
	 * c'tor
	 * @param _listTopPlayers
	 */
	public TopPlayersTableView(ArrayList<model.TopPlayers> _listTopPlayers) {
		this.listTopPlayers = _listTopPlayers;
	}
	
	/**
	 * Show top 10 player table
	 * @return
	 */
	public TableView<model.TopPlayers> show() {
		TableView<model.TopPlayers> table = new TableView<>();
		TableColumn<model.TopPlayers, String> tab1 = new TableColumn<>("TOP");
		TableColumn<model.TopPlayers, String> tab2 = new TableColumn<>("Player Name");
		TableColumn<model.TopPlayers, String> tab3 = new TableColumn<>("Player Score");
		TableColumn<model.TopPlayers, String> tab4 = new TableColumn<>("Date");
		
		// tab 1 - id
		tab1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<model.TopPlayers,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<model.TopPlayers, String> arg0) {
				model.TopPlayers x = arg0.getValue();
				if(x != null) {
					return new SimpleStringProperty(Integer.toString(listTopPlayers.indexOf(x) + 1));
				} else 
				{
					return new SimpleStringProperty("");
				}
			}
		});
		table.getColumns().add(tab1);
		
		// tab 2 - player name
		tab2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<model.TopPlayers,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<model.TopPlayers, String> arg0) {
				model.TopPlayers x = arg0.getValue();
				if(x != null) {
					return new SimpleStringProperty(x.getPlayerName());
				} else 
				{
					return new SimpleStringProperty("");
				}
			}
		});
		table.getColumns().add(tab2);
		
		// tab 3 - player score
		tab3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<model.TopPlayers,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<model.TopPlayers, String> arg0) {
				model.TopPlayers x = arg0.getValue();
				if(x != null) {
					return new SimpleStringProperty(Integer.toString(x.getScore()));
				} else 
				{
					return new SimpleStringProperty("");
				}
			}
		});
		table.getColumns().add(tab3);
		
		// tab 4 - date
		tab4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<model.TopPlayers,String>, ObservableValue<String>>() {
					
			@Override
			public ObservableValue<String> call(CellDataFeatures<model.TopPlayers, String> arg0) {
				model.TopPlayers x = arg0.getValue();
				if(x != null) {
					return new SimpleStringProperty(x.getTimeDate());
				} else 
				{
					return new SimpleStringProperty("");
				}
			}
		});
		table.getColumns().add(tab4);
		
		
		table.getItems().addAll(this.listTopPlayers);
		table.setMaxHeight(300);
		
		return table;
	}
}
