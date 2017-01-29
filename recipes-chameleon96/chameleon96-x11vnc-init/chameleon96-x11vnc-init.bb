SUMMARY = "Chameleon96 X11VNC Startup"
DESCRIPTION = "Chameleon96 scripts to start x11vnc"
AUTHOR = "Dan Negvesky <dnegvesky@arrow.com>"
SECTION = "chameleon96"

PV = "1.0"
PR = "r0"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://chameleon96-x11vnc-init.service \
           file://chameleon96-x11vnc-init.sh \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}

}

FILES_${PN} = "${base_libdir}/systemd/system/chameleon96-x11vnc-init.service \
               ${sysconfdir}/systemd/ \
               ${bindir}/chameleon96-x11vnc-init.sh \
              "

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "chameleon96-x11vnc-init.service"
