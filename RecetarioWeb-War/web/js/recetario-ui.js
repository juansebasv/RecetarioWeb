/* ==========================================================================
   recetario-ui.js  ·  Realces de interfaz (SOLO front, vanilla JS)
   - barra de menu compacta al hacer scroll + entrada escalonada
   - menu hamburguesa en movil (inyectado, sin tocar el JSP)
   - barra de progreso de lectura
   - aparicion progresiva (reveal) de secciones y tarjetas
   - tarjetas con inclinacion 3D y foco de luz que sigue al cursor
   - botones con brillo bajo el cursor + efecto ripple
   - sustituto para imagenes externas rotas
   - TOASTS + overlay de bienvenida al iniciar sesion y aviso al cerrarla
   Defensivo: si algo falla, la pagina sigue funcionando igual.
   ========================================================================== */
(function () {
    "use strict";

    var RM = window.matchMedia && window.matchMedia("(prefers-reduced-motion: reduce)").matches;

    var ready = function (fn) {
        if (document.readyState !== "loading") { fn(); }
        else { document.addEventListener("DOMContentLoaded", fn); }
    };

    document.documentElement.classList.add("rui-js");

    ready(function () {
        try { navbarScroll(); }    catch (e) {}
        try { navStagger(); }       catch (e) {}
        try { mobileMenu(); }       catch (e) {}
        try { scrollProgress(); }   catch (e) {}
        try { revealOnScroll(); }   catch (e) {}
        try { pointerFx(); }        catch (e) {}
        try { buttonFx(); }         catch (e) {}
        try { fixBrokenImages(); }  catch (e) {}
        try { smoothAnchors(); }    catch (e) {}
        try { authFeedback(); }     catch (e) {}
    });

    /* ------------------------------------------------ helpers ---------- */
    function getCookie(name) {
        var m = document.cookie.match(new RegExp("(?:^|; )" + name + "=([^;]*)"));
        return m ? decodeURIComponent(m[1]) : "";
    }
    function cap(s) { return s ? s.charAt(0).toUpperCase() + s.slice(1) : s; }

    /* ------------------------------------------------ navbar ---------- */
    function navbarScroll() {
        var onScroll = function () {
            var y = window.pageYOffset || document.documentElement.scrollTop || 0;
            document.body.classList.toggle("rui-scrolled", y > 60);
        };
        window.addEventListener("scroll", onScroll, { passive: true });
        onScroll();
    }

    function navStagger() {
        var items = document.querySelectorAll(".menuItem");
        items.forEach(function (li, i) {
            setTimeout(function () { li.classList.add("rui-navin"); }, 80 + i * 55);
        });
        /* seguridad: nunca dejar items ocultos */
        setTimeout(function () {
            items.forEach(function (li) { li.classList.add("rui-navin"); });
        }, 1500);
    }

    /* ------------------------------------------------ hamburguesa ----- */
    function mobileMenu() {
        var nav = document.querySelector(".navbar-nav");
        var host = document.querySelector(".navbar.navbar-inverse.navbar-static-top .navArea")
                 || document.querySelector(".navbar.navbar-inverse.navbar-static-top");
        if (!nav || !host) { return; }

        nav.classList.add("rui-collapsed");

        var btn = document.createElement("button");
        btn.type = "button";
        btn.className = "rui-nav-toggle";
        btn.setAttribute("aria-label", "Abrir menu");
        btn.setAttribute("aria-expanded", "false");
        btn.innerHTML = "<span></span>";
        host.appendChild(btn);

        btn.addEventListener("click", function () {
            var open = nav.classList.toggle("rui-open");
            btn.classList.toggle("rui-open", open);
            btn.setAttribute("aria-expanded", open ? "true" : "false");
        });
        nav.addEventListener("click", function (ev) {
            if (ev.target.closest("a")) {
                nav.classList.remove("rui-open");
                btn.classList.remove("rui-open");
            }
        });
    }

    /* ------------------------------------------------ progreso ------- */
    function scrollProgress() {
        var bar = document.createElement("div");
        bar.className = "rui-progress";
        document.body.appendChild(bar);
        var upd = function () {
            var h = document.documentElement;
            var max = (h.scrollHeight - h.clientHeight) || 1;
            var p = Math.min(100, Math.max(0, (h.scrollTop || window.pageYOffset) / max * 100));
            bar.style.width = p + "%";
        };
        window.addEventListener("scroll", upd, { passive: true });
        window.addEventListener("resize", upd);
        upd();
    }

    /* ------------------------------------------------ reveal -------- */
    function revealOnScroll() {
        var targets = document.querySelectorAll(
            ".papers, .specialties .table tr, .col-md-6, .col-md-4, .col-md-3"
        );
        if (!targets.length) { return; }
        var showAll = function () { targets.forEach(function (el) { el.classList.add("rui-in"); }); };

        if (!("IntersectionObserver" in window) || RM) {
            targets.forEach(function (el) { el.classList.add("rui-reveal", "rui-in"); });
            return;
        }
        var io = new IntersectionObserver(function (entries) {
            entries.forEach(function (entry) {
                if (entry.isIntersecting) { entry.target.classList.add("rui-in"); io.unobserve(entry.target); }
            });
        }, { threshold: 0.1, rootMargin: "0px 0px -6% 0px" });

        targets.forEach(function (el, i) {
            el.classList.add("rui-reveal");
            el.style.transitionDelay = Math.min(i % 6, 5) * 55 + "ms";
            io.observe(el);
        });
        setTimeout(showAll, 2200);
        window.addEventListener("load", function () { setTimeout(showAll, 400); });
    }

    /* ------------------------------ tilt 3D + spotlight en tarjetas -- */
    function pointerFx() {
        if (RM) { return; }
        var cards = document.querySelectorAll(".papers");
        cards.forEach(function (card) {
            card.addEventListener("mousemove", function (e) {
                var r = card.getBoundingClientRect();
                var px = (e.clientX - r.left) / r.width;
                var py = (e.clientY - r.top) / r.height;
                card.style.setProperty("--rui-mx", (px * 100) + "%");
                card.style.setProperty("--rui-my", (py * 100) + "%");
                card.style.setProperty("--rui-rx", ((px - 0.5) * 8).toFixed(2) + "deg");
                card.style.setProperty("--rui-ry", ((0.5 - py) * 8).toFixed(2) + "deg");
            });
            card.addEventListener("mouseleave", function () {
                ["--rui-mx", "--rui-my", "--rui-rx", "--rui-ry"].forEach(function (p) {
                    card.style.removeProperty(p);
                });
            });
        });
    }

    /* ------------------------------ botones: brillo + ripple -------- */
    function buttonFx() {
        var sel = ".btn, input[type=submit], input[type=button], button:not(.rui-nav-toggle)";
        document.addEventListener("mousemove", function (e) {
            var b = e.target.closest && e.target.closest(sel);
            if (!b) { return; }
            var r = b.getBoundingClientRect();
            b.style.setProperty("--rui-bx", ((e.clientX - r.left) / r.width * 100) + "%");
            b.style.setProperty("--rui-by", ((e.clientY - r.top) / r.height * 100) + "%");
        });
        document.addEventListener("click", function (e) {
            var b = e.target.closest && e.target.closest(sel);
            if (!b || RM) { return; }
            var r = b.getBoundingClientRect();
            var d = Math.max(r.width, r.height);
            var s = document.createElement("span");
            s.className = "rui-ripple";
            s.style.width = s.style.height = d + "px";
            s.style.left = (e.clientX - r.left - d / 2) + "px";
            s.style.top = (e.clientY - r.top - d / 2) + "px";
            if (getComputedStyle(b).position === "static") { b.style.position = "relative"; }
            b.appendChild(s);
            setTimeout(function () { s.remove(); }, 650);
        });
    }

    /* ------------------------------ imagenes externas rotas -------- */
    function fixBrokenImages() {
        var EXTERNAL = /^https?:\/\/|wowthemes|dummies?\b|placeholder/i;
        document.querySelectorAll("img").forEach(function (img) {
            var src = img.getAttribute("src") || "";
            if (!EXTERNAL.test(src)) { return; }
            var swap = function () {
                if (img.dataset.ruiFixed) { return; }
                img.dataset.ruiFixed = "1";
                var w = img.getAttribute("width") || 210;
                var h = img.getAttribute("height") || 210;
                img.classList.add("rui-img-fallback");
                img.src = "img/logo.png";
                img.style.objectFit = "contain";
                img.style.padding = "22px";
                img.setAttribute("width", w);
                img.setAttribute("height", h);
            };
            img.addEventListener("error", swap);
            if (img.complete && img.naturalWidth === 0) { swap(); }
        });
    }

    /* ------------------------------ anclas suaves ----------------- */
    function smoothAnchors() {
        document.addEventListener("click", function (ev) {
            var a = ev.target.closest('a[href^="#"]');
            if (!a) { return; }
            var id = a.getAttribute("href");
            if (id.length < 2) { return; }
            var el = document.querySelector(id);
            if (!el) { return; }
            ev.preventDefault();
            el.scrollIntoView({ behavior: RM ? "auto" : "smooth", block: "start" });
        });
    }

    /* ================= TOASTS ==================================== */
    function toastWrap() {
        var w = document.querySelector(".rui-toast-wrap");
        if (!w) {
            w = document.createElement("div");
            w.className = "rui-toast-wrap";
            document.body.appendChild(w);
        }
        return w;
    }
    function toast(title, msg, type) {
        var t = document.createElement("div");
        t.className = "rui-toast rui-" + (type || "info");
        t.innerHTML =
            '<span class="rui-t-ico"></span>' +
            '<div class="rui-t-body"><div class="rui-t-title"></div><div class="rui-t-msg"></div></div>';
        t.querySelector(".rui-t-title").textContent = title;
        t.querySelector(".rui-t-msg").textContent = msg || "";
        toastWrap().appendChild(t);
        requestAnimationFrame(function () { t.classList.add("rui-show"); });
        var kill = function () {
            t.classList.add("rui-hide");
            setTimeout(function () { t.remove(); }, 500);
        };
        setTimeout(kill, 4200);
        t.addEventListener("click", kill);
    }

    /* ================= BIENVENIDA / DESPEDIDA =================== */
    function splash(icon, title, sub) {
        if (RM) { return; }
        var s = document.createElement("div");
        s.className = "rui-splash";
        s.innerHTML =
            '<div class="rui-splash-card">' +
            '<span class="rui-splash-ico">' + icon + '</span>' +
            '<div class="rui-splash-title"></div>' +
            '<div class="rui-splash-sub"></div>' +
            '</div>';
        s.querySelector(".rui-splash-title").textContent = title;
        s.querySelector(".rui-splash-sub").textContent = sub || "";
        document.body.appendChild(s);
        setTimeout(function () { s.classList.add("rui-out"); }, 1500);
        setTimeout(function () { s.remove(); }, 2200);
    }

    function authFeedback() {
        var ref = document.referrer || "";
        var path = location.pathname.toLowerCase();
        var fromLogin = /\/sesioncontroller$/.test(ref.toLowerCase());
        var fromLogout = /\/logoutservlet$/.test(ref.toLowerCase());
        var onHome = /home_(admin|user)\.jsp$/.test(path);
        var onIndex = /(\/|index\.jsp)$/.test(path) && !onHome;
        var nick = cap(getCookie("nickname"));

        if (fromLogin && onHome) {
            splash("🍳", "Bienvenido" + (nick ? ", " + nick : "") + "!", "Sesion iniciada");
            toast("Sesion iniciada", "Que disfrutes cocinando, " + (nick || "chef") + ".", "success");
            return;
        }
        if (fromLogin && onIndex) {
            toast("No pudimos iniciar sesion", "Revisa tu usuario y contrasena e intenta de nuevo.", "error");
            return;
        }
        if (fromLogout) {
            splash("👋", "Hasta pronto!", "Sesion cerrada");
            toast("Sesion cerrada", "Tu sesion se cerro correctamente. Vuelve pronto.", "info");
        }
    }
})();
