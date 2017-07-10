require recipes-bsp/u-boot/u-boot-socfpga-common.inc                                               
require recipes-bsp/u-boot/u-boot-socfpga-env.inc                                                  
require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc 

PR = "r15"

FILESEXTRAPATHS_prepend := "${THISDIR}/files/v2017.03:"

DEPENDS += "u-boot-mkimage-native"

#SRCREV = "b24cf8540a85a9bf97975aadd6a7542f166c78a3"
SRCREV = "8537ddd769f460d7fb7a62a3dcc9669049702e51"

SRCREV_FORMAT = "hardware"
SRCREV_hardware = "${AUTOREV}"

# For a custom design or modifications to the GHRD, specify
# your remote or local location of your FPGA design project
# by using the appropriate FPGA_DESIGN_ variable below
# for a local or remote design.  Refer to the Bitbake User
# Manual "Fetchers" section for more details.

#FPGA_DESIGN_LOCAL ?= "file:///absolute/path/to/your/local/FPGA/project"
# These are specific to git fetcher; local design only requires path to file location
FPGA_DESIGN_REMOTE ?= "git://github.com/dnegvesky/chameleon96-hardware.git"
#FPGA_DESIGN_REMOTE ?= "git://github.com/arrow-socfpga/chameleon96-hardware.git"
FPGA_DESIGN_NAME ?= "hardware"
FPGA_DESIGN_BRANCH ?= "rm-loanio"

# Using a custom FPGA design stored locally might look like this:
#SRC_URI_append = "\
#	${FPGA_DESIGN_LOCAL} \
#	"

# The chameleon96 build fetches the GHRD files from the github repo specified by ${FPGA_DESIGN_REMOTE}
SRC_URI_append = "\
	file://0001-arm-socfpga-Move-CONFIG_EXTRA_ENV_SETTINGS-to-common.patch \
	file://0002-arm-socfpga-add-support-for-Arrow-Chameleon96-board.patch \
	file://0006-arm-socfpga-fix-issue-with-warm-reset-when-CSEL-is-0.patch \
	${FPGA_DESIGN_REMOTE};destsuffix=${FPGA_DESIGN_NAME};name=${FPGA_DESIGN_NAME};branch=${FPGA_DESIGN_BRANCH} \
	"

# Update project specific files in uboot 
do_configure_append_chameleon96() {
	${WORKDIR}/git/arch/arm/mach-socfpga/qts-filter.sh \
	    cyclone5 \
	    ${WORKDIR}/${FPGA_DESIGN_NAME}/ \
	    ${WORKDIR}/${FPGA_DESIGN_NAME}/software/preloader/ \
	    ${WORKDIR}/git//board/arrow/chameleon96/qts/
}

DEPENDS += "dtc-native"                                                         
DEPENDS += "u-boot-mkimage-native"
