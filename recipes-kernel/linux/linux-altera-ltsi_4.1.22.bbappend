PR = "r0"
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"
# ${PN} expands to linux-altera-ltsi, so a subdirectory with this name must exist if using next line
#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# this results in error
#SRC_URI_append += " \
#			file://${KBRANCH};type=kmeta;destsuffix=${KBRANCH} \
#			"

# bitbake is not finding the file in ./dts; copied .dts file to linux/config ...that's because FILESEXTRAPATHS_prepend was pointing to /config ...
# still need to patch the dts makefile to include this .dts
SRC_URI_append_chameleon96 = " file://socfpga_cyclone5_chameleon96.dts"

#do_patch_append () {                                                            
##        cp ${WORKDIR}/devicetrees/*.dts ${S}/arch/arm/boot/dts
#        cp ${WORKDIR}/config/*.dts ${S}/arch/arm/boot/dts                          
#}  

