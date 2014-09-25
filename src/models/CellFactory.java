ackage models;

public class CellFactory {
        public static Cell getCell(String criteria, int state) {
                if(state == 0) {
                        return null;
                }
                if(criteria.equals("GameofLife")) {
                        return new GameOfLifeCell(state);
                }
                
                if(criteria.equals("FireSimulation")) {
                        if(state == 1) {
                                return new TreeCell(state);
                        }
                        else
                                return new FireCell(state);
                }
                
                if(criteria.equals("Segregation")) {
                        if(state ==1) {
                                return new RaceOneCell(state);
                        }
                        else{
                                return new RaceTwoCell(state);
                        }
                }
                
                else
                        return null;
        }
}