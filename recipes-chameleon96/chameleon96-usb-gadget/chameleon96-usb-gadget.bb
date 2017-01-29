SUMMARY = "Chameleon96 Units to initialize USB gadgets"
DESCRIPTION = "Chameleon96 scripts to start USB gadget for USB mass storage, Ethernet, and serial interfaces"
AUTHOR = "Dan Negvesky <dnegvesky@arrow.com>"
SECTION = "chameleon96"

PR = "r1"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
SRCREV = "${AUTOREV}"

PV = "1.0${PR}+git${SRCPV}"

#SRC_URI = "file://atlassoc-gadget-init.service \
#           file://udhcpd.conf \
#           file://g-ether-load.sh \
#           git://github.com/dwesterg/atlas-drivers.git \
#          "

SRC_URI = "file://chameleon96-gadget-init.service \
           file://chameleon96-gadget-init.sh \
           file://fat_files.img.tgz \
           file://udhcpd.conf \
          "

do_install() {
	install -d ${D}${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/*.service ${D}${base_libdir}/systemd/system

	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/*.conf ${D}${sysconfdir}

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/*.sh ${D}${bindir}

	install -d ${D}${sysconfdir}/systemd/system/getty.target.wants
	( cd ${D}${sysconfdir}/systemd/system/getty.target.wants && ln -s /lib/systemd/system/serial-getty@.service serial-getty@ttyGS0.service )

	install -d ${D}${datadir}/${PN}
	tar -xzvf ${WORKDIR}/fat_files.img.tgz -C ${D}${datadir}/${PN}
}

PACKAGES =+ "${PN}-network ${PN}-udhcpd"

ALLOW_EMPTY_${PN} = "1"

FILES_${PN} = "${base_libdir}/systemd/system/chameleon96-gadget-init.service \
               ${sysconfdir}/systemd/ \
               ${datadir}/${PN}/ \
               ${datadir}/${PN}/fat_image.img \
              "

FILES_${PN}-network = "${base_libdir}/systemd/system/network-gadget-init.service \
                       ${bindir}/chameleon96-gadget-init.sh \
		       ${datadir}/${PN}"

FILES_${PN}-udhcpd = "${sysconfdir}/udhcpd.conf"

RRECOMMENDS_${PN} = "${PN}-network ${PN}-udhcpd"
RREPLACES_${PN} = "${PN}-storage"


NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "chameleon96-gadget-init.service"
