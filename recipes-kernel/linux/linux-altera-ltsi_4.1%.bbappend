PR = "r0"
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"
# ${PN} expands to linux-altera-ltsi, so a subdirectory with this name must exist if using next line
#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# this results in error
#SRC_URI_append += " \
#			file://${KBRANCH};type=kmeta;destsuffix=${KBRANCH} \
#			"

# bitbake is not finding the file in ./dts; copied .dts file to linux/config ...that's because FILESEXTRAPATHS_prepend was pointing to /config ...

SRC_URI_append_chameleon96 = " file://socfpga_cyclone5_chameleon96.dts"

do_compile_prepend () {                                                            

        cp ${WORKDIR}/*.dts ${S}/arch/${ARCH}/boot/dts                          
}  

