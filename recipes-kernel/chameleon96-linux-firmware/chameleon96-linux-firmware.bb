SUMMARY = "Firmware for the Arrow Chameleon96 FPGA"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

inherit deploy

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRCREV_FORMAT = "hardware"
SRCREV_hardware = "${AUTOREV}"

PV="${PN}+git${SRCPV}"

#SRC_URI += " \
#	git://github.com/arrow-socfpga/chameleon96-hardware.git;destsuffix=hardware;name=hardware;protocol=https;branch=master \
#"
SRC_URI += " \
	git://github.com/dnegvesky/chameleon96-hardware.git;destsuffix=hardware;name=hardware;protocol=https;branch=hdmi-test \
"

do_install () {
        cd ${WORKDIR}/hardware
	install -d ${D}${base_libdir}/firmware
        install -m 0644 output_files/chameleon96.rbf ${D}${base_libdir}/firmware
        install -m 0644 devicetrees/chameleon96.dtbo ${D}${base_libdir}/firmware
}

do_deploy () {
        cd ${WORKDIR}/hardware
	install -d ${DEPLOYDIR}
	install -m 0644 output_files/chameleon96.rbf ${DEPLOYDIR}
}

addtask deploy after do_install

FILES_${PN} = "${base_libdir}/firmware"
