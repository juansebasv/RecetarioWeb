-- ===========================================================================
-- Datos semilla AMPLIADOS de RecetarioWeb.
-- Textos largos (sin llegar al limite de columna) para probar la UI con
-- contenido realista: descripcion varchar(1000), ingredientes varchar(1000),
-- textocomen varchar(2000).
--
-- Login (SesionController) -> username + pass en texto plano:
--   admin     / admin123     rol 1  (panel administrador)
--   laura     / laura123     rol 2
--   carlos    / carlos123    rol 2
--   marta     / marta123     rol 2
--   nico      / nico123      rol 2
--   valentina / valentina123 rol 2
--   andres    / andres123    rol 2
--
-- receta.idcatreceta referencia categoria.idcat.
-- comentario.idrecetacomen referencia receta.idreceta.
-- membrecia / receta.iduserreceta / tip.idusertip referencian persona.codigo.
-- ===========================================================================

-- ------------------------------------------------------------------ personas
INSERT INTO persona (codigo, idpersona, nombre, username, pass, fechanacimeinto, email, direccion, pais, ciudad, rol, activo) VALUES
('admin',     0, 'Administrador General', 'admin',     'admin123',     DATE '1990-01-15', 'admin@recetarioweb.com',     'Calle 1 # 1-01 Oficina 301', 'Colombia', 'Bogota',   1, FALSE),
('laura',     1, 'Laura Gomez Restrepo',  'laura',     'laura123',     DATE '1995-06-20', 'laura.gomez@example.com',    'Carrera 10 # 20-30 Apto 502','Colombia', 'Medellin', 2, FALSE),
('carlos',    2, 'Carlos Ruiz Pena',      'carlos',    'carlos123',    DATE '1988-11-02', 'carlos.ruiz@example.com',    'Avenida 5 Norte # 45-67',   'Colombia', 'Cali',     2, FALSE),
('marta',     3, 'Marta Diaz Villalba',   'marta',     'marta123',     DATE '1992-03-08', 'marta.diaz@example.com',     'Calle 80 # 12-34 Interior 4','Mexico',   'CDMX',     2, FALSE),
('nico',      4, 'Nicolas Prieto Salas',  'nico',      'nico123',      DATE '2000-09-25', 'nico.prieto@example.com',    'Diagonal 7 # 8-9 Casa 12',  'Espana',   'Madrid',   2, FALSE),
('valentina', 5, 'Valentina Ochoa Mesa',  'valentina', 'valentina123', DATE '1997-12-11', 'valentina.ochoa@example.com','Transversal 22 # 5-18',     'Colombia', 'Barranquilla', 2, FALSE),
('andres',    6, 'Andres Camargo Lopez',  'andres',    'andres123',    DATE '1985-04-30', 'andres.camargo@example.com', 'Carrera 50 # 100-25 Torre B','Argentina','Buenos Aires', 2, FALSE);

-- ---------------------------------------------------------------- categorias
INSERT INTO categoria (nombrecat, idcat, fechacat, descripcion) VALUES
('Postres y reposteria', 0, DATE '2024-01-10',
 'Un rincon dedicado por completo al dulce, donde el azucar deja de ser un ingrediente para convertirse en lenguaje. Aqui encontraras tortas humedas que se deshacen en el paladar, cremas suaves montadas con paciencia, galletas con el borde crocante y el centro tierno, y postres de cuchara pensados para compartir despues de la comida. Cada receta explica los puntos criticos que suelen arruinar un dulce casero: la temperatura exacta del horno, el momento justo para retirar la preparacion, como evitar que una crema se corte y por que reposar la masa cambia la textura final. Ideal para quienes se inician en la reposteria y tambien para quienes ya tienen oficio y buscan afinar detalles.'),
('Entradas y aperitivos', 1, DATE '2024-01-10',
 'La primera impresion de una mesa casi siempre se juega en el primer bocado. En esta categoria reunimos entradas frias y calientes, tablas para picar, bocaditos para recibir invitados y aperitivos que se preparan con antelacion para que el anfitrion disfrute la reunion en lugar de vivir en la cocina. Hay opciones ligeras a base de vegetales frescos, otras mas contundentes con masas y quesos, y varias que rescatan la tradicion de la cocina de calle. Cada ficha incluye sugerencias de presentacion, maridaje sencillo y trucos para servir la cantidad correcta sin que sobre ni falte.'),
('Platos fuertes', 2, DATE '2024-01-10',
 'El corazon de cualquier comida importante. Reunimos aqui guisos de coccion lenta que perfuman toda la casa, arroces melosos, carnes selladas y terminadas al horno, y preparaciones regionales que cuentan la historia de un territorio en cada cucharada. Encontraras recetas para el almuerzo de un domingo en familia y tambien versiones mas rapidas para entre semana sin renunciar al sabor. Explicamos como construir capas de sabor desde el sofrito, cuando salar, como lograr un fondo sabroso y de que manera descansar una carne para que quede jugosa.'),
('Bebidas y cocteleria', 3, DATE '2024-01-10',
 'Desde jugos naturales y limonadas de la abuela hasta cocteles de autor y bebidas calientes para las tardes frias. En esta seccion tratamos la bebida con el mismo respeto que un plato: proporciones medidas, hielo de calidad, frutas en su punto y equilibrio entre lo dulce, lo acido y lo amargo. Hay recetas sin alcohol pensadas para toda la familia, opciones para sorprender en una celebracion y preparaciones batch para servir a muchas personas sin improvisar. Cada receta indica el vaso adecuado, la guarnicion y como escalar las cantidades.'),
('Cocina vegana y saludable', 4, DATE '2024-01-10',
 'Comida de origen vegetal que no se define por lo que le falta sino por lo que aporta. Bowls completos y coloridos, legumbres bien condimentadas, salsas cremosas sin lacteos, panes integrales y opciones para llevar al trabajo. El enfoque es practico: como lograr saciedad real, de donde sacar proteina, como dar textura y umami sin productos de origen animal y de que forma organizar la despensa para cocinar rapido entre semana. Recetas aptas para quienes ya llevan tiempo en esta cocina y tambien para curiosos que quieren sumar mas vegetales a su dia.');

-- ------------------------------------------------------------------- recetas
INSERT INTO receta (nombrereceta, idreceta, iduserreceta, idcatreceta, descripcionreceta, autorreceta, fechareceta, imagenreceta, ingredientes) VALUES
('Brownie de chocolate belga con nueces caramelizadas', 0, 'laura', 0,
 'Un brownie denso y humedo, con una capa superior finamente crujiente y un interior casi de trufa. El secreto esta en usar chocolate de buena cobertura, batir los huevos con el azucar hasta que blanqueen y no pasarse un minuto de horno. Las nueces se caramelizan aparte con una pizca de sal para que aporten contraste y un amargor elegante que corta el dulce. Se sirve tibio, cortado en cuadros generosos, y gana muchisimo acompanado de un helado de vainilla que se derrite despacio sobre la superficie. Reposar el molde diez minutos antes de desmoldar evita que se quiebre.',
 'Laura Gomez', DATE '2024-02-01', 'img/logo.png',
 '200 g de chocolate amargo 60 por ciento-180 g de mantequilla sin sal-4 huevos a temperatura ambiente-220 g de azucar-90 g de harina de trigo-30 g de cacao en polvo-1 cucharadita de esencia de vainilla-1 pizca de sal-120 g de nueces-2 cucharadas de azucar para caramelizar'),
('Tiramisu clasico con mascarpone y espresso', 1, 'laura', 0,
 'El postre italiano por excelencia, montado en capas que alternan bizcochos de soletilla empapados en cafe fuerte y una crema aireada de mascarpone. La clave para que no quede pesado es batir las yemas con el azucar al bano maria hasta lograr una sabayon sedosa y despues integrar el mascarpone sin trabajarlo de mas. Los bizcochos se sumergen apenas un segundo por lado: si se pasan, el postre se vuelve una sopa. Necesita minimo seis horas de frio, idealmente toda la noche, para que los sabores se asienten y el corte quede limpio. Se termina con una lluvia generosa de cacao amargo justo antes de servir.',
 'Laura Gomez', DATE '2024-02-03', 'img/logo.png',
 '300 g de queso mascarpone frio-4 yemas de huevo-90 g de azucar-1 taza de cafe espresso cargado y frio-24 bizcochos de soletilla-2 cucharadas de cacao amargo en polvo-1 cucharada de licor de cafe opcional'),
('Cheesecake horneado de frutos rojos', 2, 'valentina', 0,
 'Version horneada al estilo neoyorquino, con una base de galleta y mantequilla bien compacta y un relleno cremoso que se cuaja lento en el horno para que no se agriete. El horneado a baja temperatura y el bano maria son los dos aliados que evitan la temida grieta en la superficie. Encima lleva una compota de frutos rojos apenas endulzada, cocida hasta que espese pero conservando trozos enteros de fruta que aportan acidez y frescura. Se deja enfriar dentro del horno apagado con la puerta entreabierta y luego varias horas de nevera. El resultado es firme al corte y a la vez untuoso.',
 'Valentina Ochoa', DATE '2024-02-05', 'img/logo.png',
 '200 g de galletas tipo maria-90 g de mantequilla derretida-600 g de queso crema a temperatura ambiente-160 g de azucar-3 huevos-200 ml de crema de leche-1 cucharada de maicena-ralladura de 1 limon-250 g de frutos rojos mixtos-2 cucharadas de azucar para la compota'),
('Ensalada Cesar con pollo a la plancha y aderezo casero', 3, 'carlos', 1,
 'La ensalada Cesar bien hecha es un ejercicio de equilibrio entre lo cremoso, lo salado y lo crujiente. El aderezo se emulsiona a mano con yema, mostaza, ajo, limon, aceite de oliva y un toque de pasta de anchoa que aporta profundidad sin que se note el pescado. La lechuga romana debe estar muy fria y bien seca para que el aderezo se adhiera sin marchitarla. Los crutones se hacen en casa con pan del dia anterior y ajo, y el pollo se sella a fuego alto para que quede dorado por fuera y jugoso por dentro. Se termina con lascas de parmesano curado y pimienta recien molida.',
 'Carlos Ruiz', DATE '2024-02-07', 'img/logo.png',
 '2 cogollos de lechuga romana-2 pechugas de pollo-4 rebanadas de pan rustico-50 g de queso parmesano-1 yema de huevo-1 cucharadita de mostaza dijon-1 diente de ajo-el jugo de 1 limon-80 ml de aceite de oliva virgen extra-2 filetes de anchoa-sal y pimienta negra'),
('Empanadas vallunas de carne desmechada', 4, 'valentina', 1,
 'Empanadas de masa de maiz amarillo, fritas hasta quedar doradas y sonoras al morder. El relleno es una carne cocida largo rato con hogao de cebolla, tomate y comino, luego desmechada y mezclada con papa criolla para que quede jugosa y rinda. La masa se amasa con caldo tibio de la misma carne, lo que le da color y sabor, y se sella con cuidado para que no se abran en el aceite. Se acompanan con aji casero de cilantro, cebolla larga y un chorrito de vinagre. Son la entrada perfecta para una reunion porque se arman con antelacion y se frien en el momento.',
 'Valentina Ochoa', DATE '2024-02-09', 'img/logo.png',
 '500 g de harina de maiz precocida amarilla-600 g de posta de res-2 papas criollas-1 cebolla cabezona-2 tomates maduros-1 rama de cebolla larga-1 cucharadita de comino-1 cubo de caldo-aceite abundante para freir-sal al gusto'),
('Ajiaco santafereno con pollo, guascas y mazorca', 5, 'admin', 2,
 'El plato insignia de Bogota, una sopa espesa y reconfortante que combina tres tipos de papa: una que se deshace y da cuerpo, otra que aporta almidon y una tercera que se mantiene entera. Las guascas, hierba local de aroma inconfundible, son el ingrediente que lo vuelve ajiaco y no otra sopa. Se sirve muy caliente con presa de pollo desmechada, trozos de mazorca tierna, y aparte se llevan a la mesa crema de leche, alcaparras y aguacate para que cada comensal ajuste su plato. Es comida de domingo, de sobremesa larga y de segundo plato casi obligatorio.',
 'Cocina RecetarioWeb', DATE '2024-02-11', 'img/logo.png',
 '4 presas de pollo-6 papas sabanera-6 papas pastusa-8 papas criollas-3 mazorcas tiernas-3 ramas de guascas-1 cebolla larga-2 dientes de ajo-200 ml de crema de leche-4 cucharadas de alcaparras-2 aguacates-sal y pimienta'),
('Bandeja paisa completa para compartir', 6, 'andres', 2,
 'Un solo plato que resume la abundancia de la cocina antioquena. Lleva frijoles cocidos con pezuna y platano hasta quedar cremosos, arroz blanco graneado, carne molida guisada con hogao, chicharron carnudo y crocante, chorizo asado, huevo frito de yema liquida, tajadas de platano maduro, arepa delgada, aguacate y un buen aji. No es una receta de afan: cada componente se cocina por separado y se monta al final para que todo llegue caliente. Se recomienda servir en platos amplios y advertir a los invitados que despues viene una siesta.',
 'Andres Camargo', DATE '2024-02-13', 'img/logo.png',
 '400 g de frijol cargamanto-150 g de pezuna de cerdo-1 platano verde-300 g de carne molida-300 g de chicharron-4 chorizos-4 huevos-2 platanos maduros-4 arepas delgadas-2 aguacates-2 tazas de arroz-hogao de cebolla y tomate'),
('Risotto de hongos porcini y parmesano', 7, 'marta', 2,
 'Cremoso sin llevar crema: la textura del risotto sale del almidon que suelta el arroz al removerlo con caldo caliente anadido poco a poco. Los hongos porcini secos se hidratan y su agua de remojo, colada, se suma al caldo para intensificar el sabor. El arroz se nacara primero en mantequilla y cebolla, se desglasa con vino blanco y luego empieza la paciencia de ir agregando caldo cucharon a cucharon durante unos dieciocho minutos. Fuera del fuego se hace la mantecatura con mantequilla fria y parmesano, que es lo que le da el brillo final y la untuosidad de restaurante.',
 'Marta Diaz', DATE '2024-02-15', 'img/logo.png',
 '320 g de arroz arborio-30 g de hongos porcini secos-1 litro de caldo de verduras-1 cebolla pequena-100 ml de vino blanco seco-60 g de mantequilla-60 g de queso parmesano-2 cucharadas de aceite de oliva-perejil fresco-sal y pimienta'),
('Limonada de coco cremosa estilo caribe', 8, 'marta', 3,
 'Bebida emblematica de la costa colombiana, a medio camino entre un jugo y un postre. Se licua leche de coco con jugo de limon recien exprimido, hielo y azucar hasta lograr una textura espumosa y palida. El punto exacto esta en el balance: suficiente limon para que despierte, suficiente coco para que envuelva y el hielo justo para que quede densa y muy fria sin aguarse. Se sirve de inmediato en vaso alto, con una rodaja de limon en el borde. Perfecta para acompanar fritos, pescados y cualquier comida bajo el calor.',
 'Marta Diaz', DATE '2024-02-17', 'img/logo.png',
 '400 ml de leche de coco bien fria-el jugo de 4 limones-4 cucharadas de azucar-2 tazas de hielo-1 limon para decorar'),
('Buddha bowl vegano de quinua, garbanzos y tahini', 9, 'nico', 4,
 'Un bowl pensado como comida completa: cereal, legumbre, vegetales crudos y cocidos, grasa buena y una salsa que amarra todo. La quinua se tuesta un momento antes de cocinarla para que quede suelta y con sabor a nuez. Los garbanzos se asan con pimenton y comino hasta quedar crocantes por fuera. El resto son vegetales de temporada, aguacate y hojas verdes, y encima va un aderezo de tahini, limon y ajo aclarado con agua hasta que cae como cinta. Se arma por sectores para que se vea generoso y cada quien mezcle a su gusto.',
 'Nicolas Prieto', DATE '2024-02-19', 'img/logo.png',
 '160 g de quinua-1 lata de garbanzos-1 aguacate-2 zanahorias-1 taza de col rizada-1 remolacha pequena cocida-3 cucharadas de tahini-el jugo de 1 limon-1 diente de ajo-1 cucharadita de pimenton-1 cucharadita de comino-aceite de oliva y sal');

-- ---------------------------------------------------------------------- tips
INSERT INTO tip (nombretip, idtip, idusertip, descripciontip, fechatip, autortip) VALUES
('Como lograr un bizcocho aireado y parejo', 0, 'laura',
 'La miga esponjosa depende de cuanto aire logres incorporar y de que ese aire no se escape antes de cuajar. Bate la mantequilla con el azucar varios minutos hasta que la mezcla aclare y aumente de volumen: ese cremado es la base de la estructura. Agrega los huevos de a uno, esperando que se integre cada uno antes del siguiente, para que la emulsion no se corte. Tamiza los secos al menos dos veces e incorporalos en tres tandas con movimientos envolventes, nunca batiendo. Ten el horno bien precalentado y no abras la puerta en la primera mitad de la coccion, porque el golpe de aire frio hace que el centro se hunda.',
 DATE '2024-02-02', 'Laura Gomez'),
('El sofrito, la base invisible de casi todo', 1, 'admin',
 'Muchos guisos flojos se explican por un sofrito hecho con prisa. La cebolla necesita tiempo y fuego suave para pasar de cruda y agresiva a dulce y transparente: calcula entre ocho y doce minutos removiendo cada tanto. Recien entonces entra el ajo, que se quema en segundos si se agrega antes. Si la receta lleva tomate, cocinalo hasta que pierda el agua y el color vire a un rojo mas oscuro y concentrado. Un buen sofrito ya sabe rico por si solo; si a esa altura la base es sosa, el plato final tambien lo sera. Vale la pena hacer tanda doble y congelar en porciones.',
 DATE '2024-02-04', 'Cocina RecetarioWeb'),
('Cortar cebolla sin terminar llorando', 2, 'carlos',
 'El ardor viene de compuestos de azufre que se liberan al romper las celulas y se vuelven gas al contacto con el aire. Un cuchillo muy afilado corta en lugar de aplastar, asi que rompe menos celulas y libera menos gas. Enfriar la cebolla en la nevera media hora antes ralentiza esa reaccion. Trabaja cerca de una fuente de extraccion o con una vela encendida al lado, que ayuda a quemar parte del gas. Y no te frotes los ojos con las manos: si te toca, respira por la boca un momento y sigue. Deja la raiz para el final porque ahi se concentra la mayor parte del compuesto.',
 DATE '2024-02-06', 'Carlos Ruiz'),
('Sellar carnes para un dorado uniforme', 3, 'andres',
 'El dorado profundo es sabor puro gracias a la reaccion de Maillard, y para conseguirlo hay que respetar tres reglas. Primero, la carne debe estar seca: secala con papel antes de llevarla a la sarten. Segundo, la sarten tiene que estar bien caliente y con poca grasa, de lo contrario la carne suelta agua y se cuece en lugar de dorarse. Tercero, no la muevas: apoyala y dejala quieta hasta que se despegue sola, senal de que se formo la costra. Trabaja por tandas para no bajar la temperatura del sarten y termina las piezas gruesas en el horno. Deja reposar antes de cortar.',
 DATE '2024-02-08', 'Andres Camargo'),
('Una vinagreta que no se separa', 4, 'valentina',
 'Una vinagreta es una emulsion temporal de grasa en acido, y hay trucos para que aguante montada mas tiempo. La proporcion clasica es tres partes de aceite por una de vinagre o limon, pero ajustala a tu gusto. Empieza disolviendo la sal en el acido, porque en el aceite no se disuelve. Agrega una punta de mostaza o una cucharadita de miel: ambos actuan como emulsionantes y estabilizan la mezcla. Incorpora el aceite en hilo fino mientras bates con energia, o cierra todo en un frasco y agitalo con fuerza. Si aun asi se corta, unas gotas de agua tibia y mas batido suelen recuperarla.',
 DATE '2024-02-10', 'Valentina Ochoa'),
('Templar chocolate en la cocina de casa', 5, 'laura',
 'Templar es ordenar los cristales de manteca de cacao para que el chocolate quede brillante, firme y con un quiebre limpio. El metodo del sembrado es el mas simple en casa: derrite dos tercios del chocolate picado a bano maria suave sin pasar de 45 grados, retira del fuego y agrega el tercio restante removiendo hasta que baje a unos 31 o 32 grados para chocolate negro. Trabaja rapido en un ambiente fresco, sobre marmol o una bandeja fria. Si el chocolate se espesa demasiado, un golpe corto de calor lo devuelve al punto. Un chocolate mal templado queda opaco, blando y con vetas blancas.',
 DATE '2024-02-12', 'Laura Gomez'),
('Conservar hierbas frescas por mas dias', 6, 'nico',
 'Las hierbas se marchitan por deshidratacion y por exceso de humedad estancada, asi que la clave es un termino medio. Las de tallo tierno como cilantro, perejil y albahaca duran mas paradas en un vaso con un dedo de agua, cubiertas sin apretar con una bolsa, en la nevera salvo la albahaca que prefiere temperatura ambiente. Las de tallo lenoso como tomillo y romero se conservan envueltas en un pano apenas humedo dentro de un recipiente cerrado. Lavarlas solo justo antes de usar evita que se pudran. Lo que vaya a sobrar se puede picar y congelar en cubeteras con aceite de oliva.',
 DATE '2024-02-14', 'Nicolas Prieto');

-- ------------------------------------------------------------------- empresas
INSERT INTO empresa (nombreemp, idemp, descripcionemp, imagenemp) VALUES
('Distribuidora La Cosecha', 0,
 'Operador mayorista de frutas y verduras que trabaja directamente con pequenos productores de la region. Su modelo se basa en acortar la cadena: recogen en finca por la manana y entregan en las cocinas de sus clientes al dia siguiente, lo que se traduce en producto mas fresco y en un precio mas justo para el agricultor. Manejan un catalogo estacional que cambia segun la cosecha real y no segun la demanda, y asesoran a restaurantes sobre que esta en su mejor momento cada semana. Ofrecen entregas programadas, facturacion electronica y una linea de producto imperfecto a menor precio para reducir desperdicio.',
 'img/logo.png'),
('Lacteos del Valle', 1,
 'Planta familiar de tercera generacion especializada en quesos frescos y madurados, mantequilla de cultivo y cremas. Toda la leche proviene de hatos propios y de un grupo cerrado de proveedores con los que comparten practicas de pastoreo y bienestar animal. Su producto mas conocido es un queso costeno bajo en sal pensado para asar, pero tambien elaboran madurados tipo europeo con curaciones de tres a doce meses. Venden a tiendas especializadas, hoteles y directamente al publico en su punto de fabrica, donde ademas ofrecen visitas guiadas y catas los fines de semana.',
 'img/logo.png'),
('Cafe de Origen Sierra Nevada', 2,
 'Tostador que compra cafe verde a asociaciones de productores de la Sierra Nevada y lo tuesta en pequenos lotes para conservar los perfiles de cada finca. Publican en cada bolsa la altura del cultivo, el proceso de beneficio y las notas de cata, y acompanan a sus clientes con recomendaciones de molienda y extraccion segun el metodo que usen. Tienen linea para cafeteria profesional, suscripcion mensual para hogar y un programa de formacion basica en preparacion. Parte del margen se reinvierte en mejoras de infraestructura de secado en las comunidades con las que trabajan.',
 'img/logo.png'),
('Molinos del Trigal', 3,
 'Molino artesanal que produce harinas de trigo, maiz y centeno molidas a la piedra, sin blanqueadores ni aditivos. Al conservar el germen y parte del salvado, sus harinas tienen mas sabor y aroma que las industriales, aunque exigen ajustar la hidratacion de las recetas. Trabajan con panaderias de masa madre, pizzerias y reposteros, y ofrecen fichas tecnicas con la fuerza y el contenido de proteina de cada lote. Tambien elaboran mezclas listas para pan rustico y una linea de harinas integrales molidas por encargo para quienes buscan trazabilidad completa del grano.',
 'img/logo.png'),
('Pescaderia Mar Abierto', 4,
 'Proveedor de pescado y marisco que prioriza especies de temporada y artes de pesca de bajo impacto. Reciben producto dos veces por semana, lo limpian y porcionan segun el pedido de cada cocina, y garantizan cadena de frio documentada desde el puerto. Su equipo asesora sobre sustituciones cuando una especie no esta en buen momento y publican un boletin semanal con lo que vale la pena comprar. Atienden restaurantes, pescaderias de barrio y pedidos de particulares con entrega refrigerada. Ofrecen ademas cortes menos comunes y despieces completos para aprovechar la pieza entera.',
 'img/logo.png'),
('Especias y Sabores del Mundo', 5,
 'Importador y tostador de especias que vende en volumenes pequenos para que el producto rote y llegue aromatico a la cocina. Muelen bajo pedido, arman mezclas por region y explican el uso de cada ingrediente para quienes se animan a salir de lo conocido. Tienen desde pimentones ahumados y comino tostado hasta pastas de curry y hierbas secas de montana. Trabajan con restaurantes de cocina de fusion y con tiendas gourmet, y publican recetas cortas para dar salida a especias que la gente compra y no sabe como aprovechar. Envian a todo el pais en empaque que preserva el aroma.',
 'img/logo.png');

-- --------------------------------------------------------------- comentarios
INSERT INTO comentario (textocomen, idusercomen, idrecetacomen, fechacomen) VALUES
('Hice el brownie para el cumpleanos de mi hermana y desaparecio en quince minutos. Segui el consejo de no pasarme de horno y lo saque cuando el palillo salio con migas humedas pegadas, no limpio. Quedo con esa capa fina crujiente arriba y el centro casi de trufa que promete la receta. Lo unico que cambie fue tostar un poco mas las nueces porque me gustan bien marcadas. La proxima voy a probar con un chocolate de mayor porcentaje a ver si aguanta el dulce. Gracias por explicar el paso del reposo antes de desmoldar, la vez pasada se me habia partido entero.',
 'carlos', 0, DATE '2024-02-20'),
('Muy buena base pero a mi me quedo un poco dulce, la proxima le bajo veinte gramos de azucar y subo el cacao. Igual la textura fue perfecta y el truco de caramelizar las nueces con sal es un golazo, ese amarguito equilibra todo. Lo servi con helado de vainilla como sugieren y la combinacion tibio con frio vale totalmente la pena.',
 'marta', 0, DATE '2024-02-21'),
('El tiramisu me salio de restaurante siguiendo al pie de la letra lo del sabayon al bano maria. Antes lo hacia con las yemas crudas y quedaba con sabor a huevo, ahora la crema es sedosa y sin ese gusto. Confirmo lo de mojar los bizcochos un segundo por lado nomas, la primera vez me pase y quedo aguado. Lo deje toda la noche en la nevera y al otro dia el corte salio limpio y firme. El cacao amargo al final es innegociable.',
 'valentina', 1, DATE '2024-02-22'),
('Probe la ensalada Cesar con el aderezo casero y no vuelvo al de frasco. La pasta de anchoa da profundidad sin que sepa a pescado, tal cual dice la receta. Hice los crutones con pan del dia anterior y ajo y quedaron perfectos. Un tip que agrego: secar muy bien la lechuga en centrifuga, si queda con agua el aderezo no se pega y se junta liquido en el fondo del bol.',
 'laura', 3, DATE '2024-02-23'),
('El ajiaco quedo espectacular, la clave fueron las guascas frescas que consegui en la plaza de mercado, con las secas nunca me habia quedado igual. Use los tres tipos de papa como indican y la textura quedo espesa sin necesidad de harina ni nada raro. Lo servi con las cremas, alcaparras y aguacate aparte para que cada uno armara su plato y fue un exito en el almuerzo familiar. Al otro dia estaba todavia mejor.',
 'andres', 5, DATE '2024-02-24'),
('Ame que la bandeja paisa venga explicada por componentes, la habia intentado antes y me estresaba tener todo caliente al mismo tiempo. Cocine los frijoles el dia anterior y solo los recalente, el chicharron lo hice al final para que estuviera crocante. Si, despues vino la siesta que ustedes advierten. Plato pesado pero para un domingo con la familia es imbatible.',
 'nico', 6, DATE '2024-02-25'),
('El risotto por fin me salio cremoso sin echarle crema, era cuestion de paciencia y de ir agregando el caldo de a poco sin dejar de revolver. El agua de remojo de los porcini colada le suma muchisimo sabor, no la tiren. La mantecatura fuera del fuego con mantequilla fria y parmesano es lo que le da el brillo final. Me falto un poco de sal la primera vez porque el caldo era casero y bajo en sodio, ojo con eso.',
 'carlos', 7, DATE '2024-02-26'),
('Limonada de coco de la costa tal cual la toma uno en Cartagena. El balance de limon y coco que mencionan es real, la primera vez le puse poco limon y quedo empalagosa. Bien fria y recien licuada es otra cosa, si la dejas parada se separa y pierde la espuma. La acompanamos con pescado frito y fue la combinacion perfecta para el calor de aca.',
 'valentina', 8, DATE '2024-02-27'),
('El buddha bowl se volvio mi almuerzo de oficina fijo. Dejo la quinua y los garbanzos asados hechos desde la noche anterior y en la manana solo corto lo fresco y armo. El aderezo de tahini aclarado con agua hasta que cae como cinta es la clave para que amarre todo. Rinde, llena de verdad y no te deja pesado para la tarde. Le agrego semillas de girasol por encima para un extra de textura.',
 'nico', 9, DATE '2024-02-28'),
('Segui el tip del sofrito y noto la diferencia en todos mis guisos ahora. Antes tenia el fuego alto por afan y la cebolla quedaba cruda por dentro, dando ese sabor agresivo. Doce minutos a fuego bajo y despues el ajo, tal cual. Hice tanda doble y congele en porciones como sugieren, me ahorra tiempo cada semana. Ojala saquen mas consejos de tecnica base como este.',
 'marta', 5, DATE '2024-02-29');

-- --------------------------------------------------------------- membrecias
INSERT INTO membrecia (idusermem, idmem, puntos, fechamem, activamem) VALUES
('laura',     0, 120, DATE '2024-01-20', TRUE),
('carlos',    1,  40, DATE '2024-02-03', FALSE),
('valentina', 2,  80, DATE '2024-02-05', TRUE),
('andres',    3,  20, DATE '2024-02-13', FALSE);
