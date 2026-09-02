/* ==========================================================================
   smoothscroll.js  ·  DESACTIVADO A PROPOSITO
   --------------------------------------------------------------------------
   La version original ("SmoothScroll for Chrome", ~2015) engancha los eventos
   'wheel' y 'keydown' y llama a event.preventDefault() para animar el scroll
   por su cuenta. En Chrome moderno esa animacion ya no funciona, de modo que
   dejaba la rueda del raton y las teclas de flecha/AvPag SIN efecto: la pagina
   no se podia desplazar.

   Se sustituye por este stub vacio. El scroll nativo del navegador vuelve a
   funcionar y el desplazamiento suave lo aporta ahora:
     - css/recetario-ui.css   ->  html { scroll-behavior: smooth; }
     - js/recetario-ui.js     ->  smoothAnchors()  (para los enlaces #ancla)

   Se conserva el archivo (y sus <script src> en los JSP) para no tocar ~15
   vistas; simplemente no hace nada.
   ========================================================================== */
