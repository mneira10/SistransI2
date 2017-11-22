import numpy as np
import pandas as pd
from StringIO import StringIO
# ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA

numDatos = 1000000
datos = []




for i in range(numDatos):
	temp = []
	
	temp.append(i+31)
	temp.append(21+np.random.random()*1000020)
	temp.append(np.random.random()*199999+400031)
	dia =int(np.random.random()*28//1)
	mes = int(np.random.random()*12//1)
	if(dia<10):
		sdia = "0"+str(dia)
	else:
		sdia = str(dia)
	if(mes<10):	
		smes = "0"+str(mes+1)
	else:
		smes = str(mes+1)
	
	temp.append("2017-"+   smes+"-"+sdia)	

	datos.append(temp)
	print((i+0.0)*100/numDatos)


datos = np.array(datos)

print(datos)

fmt='%s,%s,%s,%s'
np.savetxt("genHistorial.csv", datos, delimiter=",",fmt = fmt)
# 