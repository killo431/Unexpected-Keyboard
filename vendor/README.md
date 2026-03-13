# Vendor Dependencies

This directory contains third-party dependencies included as git submodules.

## cdict

C dictionary library used by Unexpected Keyboard.

- Repository: https://github.com/Julow/cdict

## termux-app

Termux terminal emulator and Android Linux environment application.

- Repository: https://github.com/termux/termux-app
- Version: v0.109 (tag: v0.109, commit: 354fe1948eaad1144513db30368f142fcb83449a)
- Added: 2026-03-13

This fork of termux-app v0.109 is included as a reference for integration with Unexpected Keyboard's Termux Run feature. The Termux Run feature allows users to execute shell commands and scripts in Termux directly from the keyboard.

### About Termux v0.109

Termux v0.109 was released on 2021-10-11 and is the minimum version required for the `termux_run` key feature in Unexpected Keyboard. This version includes:

- Support for the `com.termux.RUN_COMMAND` intent service
- The `allow-external-apps` permission system
- Terminal emulator improvements

For more information about using the Termux Run feature, see [doc/Termux-Run-Setup.md](../doc/Termux-Run-Setup.md).

## Updating Submodules

To update all submodules to their latest commits:

```bash
git submodule update --remote
```

To initialize submodules after cloning this repository:

```bash
git submodule update --init --recursive
```
