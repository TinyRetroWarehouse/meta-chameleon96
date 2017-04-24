PR = "r0"
FILESEXTRAPATHS_prepend := "${THISDIR}/config:"

SRC_URI_append_chameleon96 += " \
				file://socfpga-4.1-ltsi/cfg/altvipfb.cfg \
				file://socfpga-4.1-ltsi/cfg/block.cfg \
				file://socfpga-4.1-ltsi/cfg/cma.cfg \
				file://socfpga-4.1-ltsi/cfg/configfs.cfg \
				file://socfpga-4.1-ltsi/cfg/gpio-keys.cfg \
				file://socfpga-4.1-ltsi/cfg/led-triggers.cfg \
				file://socfpga-4.1-ltsi/cfg/usb-gadget.cfg \
				file://socfpga-4.1-ltsi/patches/0001-add-Chameleon96-devicetree.patch \
				"

