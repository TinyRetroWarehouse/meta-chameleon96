# this is not available in kraj/meta-altera so point to console-image.bb instead
# need to add additional packages below (dn 1/19/17)
#require recipes-images/angstrom/extended-console-image.bb
require recipes-images/angstrom/console-image.bb

# add next line back in (below chameleon96-lighttpd-conf \) when chameleon96 webcontent is available
#	chameleon96-webcontent (backslash)
	
IMAGE_INSTALL += " \
	bash \
	chameleon96-lighttpd-conf \
	chameleon96-usb-gadget \
	ethtool \
	gcc \
	gdb \
	gdbserver \
	gnuplot \
	i2c-tools \
	iperf \
	iw \
	kernel-dev \
	kernel-image \
	kernel-modules \
	lighttpd \
	lighttpd-module-cgi \
	ne10 \
	net-tools \
	nfs-utils-client \
	packagegroup-sdk-target \
	tar \
	usbutils \
	vim \
	vim-vimrc \
"
export IMAGE_BASENAME = "chameleon96-console-image"

#overload timestamp function in image.bbclass

rootfs_update_timestamp () {
        date -u +%4Y%2m%2d%2H%2M -d "+1 day">${IMAGE_ROOTFS}/etc/timestamp
}

EXPORT_FUNCTIONS rootfs_update_timestamp

# add later
#	chameleon96-fpga-init \
#	chameleon96-fpga-leds \
#	linux-firmware-chameleon96 \