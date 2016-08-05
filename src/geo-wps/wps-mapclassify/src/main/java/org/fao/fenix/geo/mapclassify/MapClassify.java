package org.fao.fenix.geo.mapclassify;

import org.geotools.process.factory.StaticMethodsProcessFactory;
import org.geotools.text.Text;

/**
 * MapClassify
 *
 */
public class MapClassify extends StaticMethodsProcessFactory<MapClassify> {

  public MapClassify() {
    super(Text.text("Fenix WPS"), "fenix", MapClassify.class);
  }

      
  
}
