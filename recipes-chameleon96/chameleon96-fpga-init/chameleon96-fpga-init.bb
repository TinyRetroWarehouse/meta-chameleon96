SUMMARY = "Arrow Chameleon96 FPGA Initialization"
DESCRIPTION = "Scripts to initialize the FPGA and its peripherals using devicetree overlays."
AUTHOR = "Dan Negvesky <dnegvesky@arrow.com>"
SECTION = "chameleon96"

PR = "r1"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
PV = "1.0${PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://chameleon96-fpga-init.service \
           file://chameleon96-fpga-init.sh \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}
}

ALLOW_EMPTY_${PN} = "1"

FILES_${PN}-network = "${base_libdir}/systemd/system/chameleon96-fpga-init.service \
                       ${bindir}/chameleon96-fpga-init.sh"


NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "chameleon96-fpga-init.service"
