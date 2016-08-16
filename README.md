# fenix-geo-wps
Geospatial web processing services for Fenix components
<<<<<<< HEAD

## Fenix MapClassify
This process generates a SLD in GeoServer based on the input classification of the layer choosen.

### Requirements

1. The **[SLD Service Module](http://docs.geoserver.org/stable/en/user/community/sldservice/index.html)** has to be installed in **[GeoServer](http://geoserver.org)** as community module.

### Data definition

Input:
- Layer name [required]
- Temporary result [optional] [default=yes]
	- Yes: it generates a temporary URI of HTTP SLD resource
	- No: it generates an SLD associated with the workspace of the Layer name
- Classification [required]
- ColorRamp [required]
- Intervals [required]
- Method [required]
- Data [required]
- Join Column [required]
- Join Column Label [required]
- Join Type [required]
- Decimal value [optional] [default=2]

Output:
- service [optional] [default=SLD] [SLD,WMS,WFS]
- legend [optional] 
- url [required]

### Examples

#### Clients

1. [UNECA](http://fenix.fao.org/demo/uneca/#/domains/population)

#### Legacy service

##### Request

```json
{
    "BoundingBox": "",
    "KeywordList": [],
    "LatLonBoundingBox": "",
    "Style": {
        "abstract": "",
        "legendurl": {
            "format": "",
            "onlineresource": ""
        },
        "name": "",
        "title": ""
    },
    "abstract": "",
    "colorramp": "Greens",
    "customgfi": {
        "content": {
            "EN": "<div class='fm-popup'>{{areanamee}}<div class='fm-popup-join-content'>{{{iso3}}} {{measurementunit}}</div></div>"
        },
        "showpopup": true
    },
    "decimalvalues": 2,
    "defaultgfi": true,
    "enablegfi": true,
    "format": "image/png",
    "joincolumn": "iso3",
    "joincolumnlabel": "areanamee",
    "joindata": [
        {
            "GHA": 2.5
        },
        {
            "MLI": 3.6
        },
        {
            "NGA": 2.5606
        }
    ],
    "jointype": "shaded",
    "lang": "EN",
    "layers": "fenix:gaul0_faostat3_3857",
    "layertitle": "D3P_R_0",
    "layertype": "JOIN",
    "name": "",
    "opacity": "0.7",
    "openlegend": true,
    "srs": "",
    "styles": "",
    "switchjointype": false,
    "tiitle": "",
    "transparent": "TRUE",
    "visibility": true,
    "zindex": 100
}
```

##### Response

```json
{
    "legend": [
        {
            "codes": [
                "GHA"
            ],
            "color": "#E5F5E0",
            "label": "&lt;= 2.5",
            "range": 2.5
        },
        {
            "codes": [],
            "color": "#A1D99B",
            "label": "&lt;= 2.56",
            "range": 2.56
        },
        {
            "codes": [
                "MLI",
                "NGA"
            ],
            "color": "#31A354",
            "label": "&gt; 2.56",
            "range": 2.56
        }
    ],
    "url": "http://fenix.fao.org/test/geo/fenix/mapclassify/download/sld/sld_e6328c92-fe5b-4a05-904f-34d8d74c0be9.sld"
}
```

##### SLD file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<sld:StyledLayerDescriptor version="1.0.0" xmlns="http://www.opengis.net/sld" xmlns:gml="http://www.opengis.net/gml" xmlns:ogc="http://www.opengis.net/ogc" xmlns:sld="http://www.opengis.net/sld">
	<sld:NamedLayer>
		<sld:Name>fenix:gaul0_faostat3_3857</sld:Name>
		<sld:UserStyle>
			<sld:FeatureTypeStyle>
				<sld:Rule>
					<sld:Title>&lt;= 2.5</sld:Title>
					<ogc:Filter>
						<ogc:PropertyIsEqualTo>
							<ogc:PropertyName>iso3</ogc:PropertyName>
							<ogc:Literal>GHA</ogc:Literal>
						</ogc:PropertyIsEqualTo>
					</ogc:Filter>
					<sld:PolygonSymbolizer>
						<sld:Fill>
							<sld:CssParameter name="fill">#E5F5E0</sld:CssParameter>
						</sld:Fill>
					</sld:PolygonSymbolizer>
				</sld:Rule>
				<sld:Rule>
					<sld:Title>&lt;= 2.56</sld:Title>
					<ogc:Filter>
						<ogc:PropertyIsEqualTo>
							<ogc:PropertyName>iso3</ogc:PropertyName>
							<ogc:Literal>-9999999</ogc:Literal>
						</ogc:PropertyIsEqualTo>
					</ogc:Filter>
					<sld:PolygonSymbolizer>
						<sld:Fill>
							<sld:CssParameter name="fill">#A1D99B</sld:CssParameter>
						</sld:Fill>
					</sld:PolygonSymbolizer>
				</sld:Rule>
				<sld:Rule>
					<sld:Title>&gt; 2.56</sld:Title>
					<ogc:Filter>
						<ogc:Or>
							<ogc:PropertyIsEqualTo>
								<ogc:PropertyName>iso3</ogc:PropertyName>
								<ogc:Literal>MLI</ogc:Literal>
							</ogc:PropertyIsEqualTo>
							<ogc:PropertyIsEqualTo>
								<ogc:PropertyName>iso3</ogc:PropertyName>
								<ogc:Literal>NGA</ogc:Literal>
							</ogc:PropertyIsEqualTo>
						</ogc:Or>
					</ogc:Filter>
					<sld:PolygonSymbolizer>
						<sld:Fill>
							<sld:CssParameter name="fill">#31A354</sld:CssParameter>
						</sld:Fill>
					</sld:PolygonSymbolizer>
				</sld:Rule>
			</sld:FeatureTypeStyle>
		</sld:UserStyle>
	</sld:NamedLayer>
</sld:StyledLayerDescriptor>
```

#### WPS service

##### Request

TODO

##### Response

TODO

## Fenix GeoStatistics

## Development

### Start a new WPS project

```bash
mvn -B archetype:generate \
		-DartifactId=geo-wps \
		-DgroupId=org.fao.fenix \
		-Dname="Fenix WPS module" \
		-DmoduleName=geo-wps \
		-DmoduleDescription="WPS module"
```

Change the packaging property to the mode *jar*:

```xml
<packaging>jar</packaging>
```

Then create the artifact for the new WPS:

```bash
cd geo-wps
mvn -B archetype:generate \
	-DartifactId=wps-demo \
	-DgroupId=org.fao.fenix.geo \
	-Dname="Fenix Web Processing Service for demo" \
	-Dpackage=org.fao.fenix.geo.demo \
	-DmoduleName=wps-demo \
	-DmoduleDescription="Fenix WPS module for demo"
```

You can also remove the generated **src** directory that is unnecessary.
=======
>>>>>>> parent of 6074f59... Describe how mapclassify has to be implemented
