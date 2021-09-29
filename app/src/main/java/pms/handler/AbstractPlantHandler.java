package pms.handler;

import java.util.List;
import pms.domain.Plant;

public abstract class AbstractPlantHandler implements Command {

  List<Plant> plantList;

  public AbstractPlantHandler(List<Plant> plantList) {
    this.plantList = plantList;
  }

  protected Plant findByPlantName(String name) {
    for (Plant plant : plantList) {
      if (plant.getPlantName().equals(name) ) {
        return plant;
      }
    }
    return null;
  }

}


