PR = "r5"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/v2016.11:"

DEPENDS += "u-boot-mkimage-native"

SRCREV_FORMAT = "chameleon96"
SRCREV_chameleon96 = "${AUTOREV}"

#SRC_URI_append = "\
#	file://0001-socfpga-add-support-for-Terasic-DE10-Nano-board.patch \
#	file://de10-nano.env \
#	file://de0-nano-soc.env \
#	"

SRC_URI_append = "\
	file://chameleon96.env \
	file://socfpga_chameleon96_defconfig \
	"

do_compile_prepend () {                                                            

        cp ${WORKDIR}/socfpga_chameleon96_defconfig ${S}/git/configs                          
}  

#SRC_URI_append_de0-nano = " \
#	git:///data/atlas-soc/angstrom/de10-nano-hardware.git;destsuffix=de-nano-ghrd;name=de-nano-ghrd;branch=RELEASE_BUILDS \
#"

#SRC_URI_append_de10-nano = " \
#	git:///data/atlas-soc/angstrom/de10-nano-hardware.git;destsuffix=de-nano-ghrd;name=de-nano-ghrd;branch=RELEASE_BUILDS \
#"

# Update project specific files in uboot 
#do_configure_append_de0-nano() {
#	${WORKDIR}/git/arch/arm/mach-socfpga/qts-filter.sh \
#	    cyclone5 \
#	    ${WORKDIR}/de-nano-ghrd/DE0_NANO_GHRD/ \
#	    ${WORKDIR}/de-nano-ghrd/DE0_NANO_GHRD/preloader/ \
#	    ${WORKDIR}/git//board/terasic/de0-nano-soc/qts/
#}

#do_configure_append_de10-nano() {
#	${WORKDIR}/git/arch/arm/mach-socfpga/qts-filter.sh \
#	    cyclone5 \
#	    ${WORKDIR}/de-nano-ghrd/DE10_NANO_GHRD/ \
#	    ${WORKDIR}/de-nano-ghrd/DE10_NANO_GHRD/preloader/ \
#	    ${WORKDIR}/git//board/terasic/de10-nano/qts/
#}

 
