import json
import xml.etree.ElementTree as ET
myTree = ET.parse('D://bengaluru.osm_01.osm')
myroot = myTree.getroot()
print(myroot.tag)
road_list = []
for x in myroot:
    # print(x.tag, x.attrib)
    if(x.tag == 'node'):
        nodeAttrib = x.attrib
        # print(nodeAttrib['lat'])

    for y in x:
        if(y.tag == 'tag'):
            l = list(y.attrib.values())
            if(l[0] == 'addr:street'):
                dict = {"road" : l[1], "latitude":nodeAttrib['lat'], "longitude":nodeAttrib['lon']}
                if dict not in road_list:
                    road_list.append(dict)

dict = {"road_details" : road_list}
# for x in road_list:
#     dictionary = {"road" : x}
#     dict["road_details"].append(dictionary)
#
json_object = json.dumps(dict, indent=4)
with open("D:/roadList1.json", "w") as outfile:
    outfile.write(json_object)