#!/bin/sh

modprobe g_multi file=/usr/share/chameleon96-usb-gadget/fat_files.img

sleep 5

/sbin/ifconfig usb0 hw ether 00:01:02:03:c5:96
/sbin/ifconfig usb0 192.168.96.96 netmask 255.255.255.0
/usr/sbin/udhcpd -fS -I 192.168.96.96 /etc/udhcpd.conf
