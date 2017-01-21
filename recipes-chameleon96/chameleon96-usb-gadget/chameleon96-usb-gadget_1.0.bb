SUMMARY = "Chameleon96 Units to initialize USB gadgets"
DESCRIPTION = "Chameleon96 scripts to start USB gadget for USB mass storage, Ethernet, and serial interfaces"
AUTHOR = "Dan Negvesky <dnegvesky@arrow.com>"
SECTION = "chameleon96"

PR = "r7"

inherit systemd

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

PACKAGE_ARCH = "${MACHINE_ARCH}"
SRCREV = "${AUTOREV}"

#when PV is set to include SRCPV, this results in a variable expansion error:
# ERROR: ExpansionError during parsing /home/dan/work/96boards/angstrom-v2015.12-yocto2.0/angstrom-manifest/sources/meta-chameleon96/recipes-chameleon96/chameleon96-usb-gadget/chameleon96-usb-gadget_1.0.bb: Failure expanding variable S: ExpansionError: Failure expanding variable SRCPV, expression was ${@bb.fetch2.get_srcrev(d)} which triggered exception FetchError: Fetcher failure: SRCREV was used yet no valid SCM was found in SRC_URI
#see here: http://lists.openembedded.org/pipermail/openembedded-core/2012-December/072511.html

#PV = "1.0${PR}+git${SRCPV}"
PV = "1.0"

#SRC_URI = "file://atlassoc-gadget-init.service \
#           file://udhcpd.conf \
#           file://g-ether-load.sh \
#           git://github.com/dwesterg/atlas-drivers.git \
#          "

SRC_URI = "file://chameleon96-gadget-init.service \
           file://udhcpd.conf \
           file://g-ether-load.sh \
           file://fat_files.img.tgz \
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

#	install -d ${D}${datadir}/${PN}
# need to change this for testing build with local files (i.e. not fetched from git); build error:
# | tar (child): /home/dan/work/96boards/angstrom-v2014.12-yocto1.7/setup-scripts/build/tmp-angstrom_v2014_12-glibc/work/chameleon96-angstrom-linux-gnueabi/
# chameleon96-usb-gadget/1.0-r7/git/fat_files.img.tgz: Cannot open: No such file or directory
#	tar -xzvf ${WORKDIR}/git/fat_files.img.tgz -C ${D}${datadir}/${PN}
# appears this command is not necessary since the local .tgz specified in SRC_URI above already got extracted; comment out this install command (line 45) too
#	tar -xzvf ${WORKDIR}/fat_files.img.tgz -C ${D}${datadir}/${PN}
}

PACKAGES =+ "${PN}-network ${PN}-udhcpd"

ALLOW_EMPTY_${PN} = "1"

FILES_${PN} = "${base_libdir}/systemd/system/chameleon96-gadget-init.service \
               ${sysconfdir}/systemd/ \
               ${datadir}/${PN}/ \
               ${datadir}/${PN}/fat_image.img \
              "

FILES_${PN}-network = "${base_libdir}/systemd/system/network-gadget-init.service \
                       ${bindir}/g-ether-load.sh \
		       ${datadir}/${PN}"

FILES_${PN}-udhcpd = "${sysconfdir}/udhcpd.conf"

RRECOMMENDS_${PN} = "${PN}-network ${PN}-udhcpd"
RREPLACES_${PN} = "${PN}-storage"


NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "chameleon96-gadget-init.service"
