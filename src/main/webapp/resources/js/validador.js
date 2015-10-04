$(document).ready(function() {
    $('#clienteForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nombres: {
                validators: {
                    notEmpty: {
                        message: 'El nombre es requerido'
                    },
                    stringLength: {
                        min: 3,
                        message: 'El nombre es muy corto'
                    }
                }
            },
            apellidos: {
                validators: {
                    notEmpty: {
                        message: 'El apellido es requerido'
                    },
                    stringLength: {
                        min: 3,
                        message: 'El apellido es muy corto'
                    }
                }
            },
            dni: {
                validators: {
                    notEmpty: {
                        message: 'El dni es requerido y no puede ser vacio'
                    },
                    regexp: {
                        regexp: /^\d{8}$/,
                        message: 'El dni solo consta de 8 dígitos'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'El correo es requerido y no puede ser vacio'
                    },
                    emailAddress: {
                        message: 'El correo electronico no es valido'
                    }
                }
            },
            telefono: {
                message: 'El teléfono no es valido',
                validators: {
                    notEmpty: {
                        message: 'El teléfono es requerido y no puede ser vacio'
                    },
                    stringLength: {
                        min: 6,
                        message: 'El teléfono tiene mínimo 6 digitos'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: 'El teléfono solo puede contener números'
                    }
                }
            }
        }
    });
    $('#carroForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            marca: {
                validators: {
                    notEmpty: {
                        message: 'La Marca del Auto es requerida'
                    },
                    stringLength: {
                        min: 3,
                        message: 'Marca de Auto muy corta'
                    }
                }
            },
            placa: {
                validators: {
                    notEmpty: {
                        message: 'La placa es requerida'
                    },
                    stringLength: {
                        min: 6,
                        max: 6,
                        message: 'La placa es de 6 caracteres'
                    }
                }
            },
            modelo: {
                validators: {
                    notEmpty: {
                        message: 'El Modelo de Auto es requerido'
                    }
                }
            },
            cliente: {
                validators: {
                    notEmpty: {
                        message: 'El Cliente Dueño del Auto es requerido'
                    }
                }
            }
        }
    });
    $('#usuarioForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nombreCompleto: {
                validators: {
                    notEmpty: {
                        message: 'El nombre es requerido y no puede estar vacio'
                    },
                    stringLength: {
                        min: 10,
                        message: 'El nombre es de minimo 10 caracteres'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'El nombre de usuario es requerido y no puede estar vacio'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'El nombre de usuario debe ser mayor a 6 y menor a 30 caracteres'
                    }, regexp: {
                        regexp: /[0-9-()+]{0,20}/,
                        message: 'El nombre de usuario solo puede consistir de caracteres alfanumericos, puntos y guiones'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'La contraseña es requerida y no puede estar vacia'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'La contraseña y su confirmación no son las mismas'
                    },
                    different: {
                        field: 'username',
                        message: 'La contraseña no puede ser la misma que el nombre de usuario'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'La contraseña es requerida y no puede estar vacia'
                    },
                    identical: {
                        field: 'password',
                        message: 'La contraseña y su confirmación no son las mismas'
                    },
                    different: {
                        field: 'username',
                        message: 'La contraseña no puede ser la misma que el nombre de usuario'
                    }
                }
            }
        }
    });
    $('#modeloAddForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nombre: {
                validators: {
                    notEmpty: {
                        message: 'El nombre es requerido y no puede estar vacio'
                    },
                    stringLength: {
                        min: 6,
                        message: 'El nombre tiene 6 caracteres minimo'
                    }
                }
            }
        }
    });
    $('#comboAddForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nombre: {
                validators: {
                    notEmpty: {
                        message: 'El nombre es requerido y no puede estar vacio'
                    },
                    stringLength: {
                        min: 6,
                        message: 'El nombre tiene 6 caracteres minimo'
                    }
                }
            },
            numeroLavadas: {
                validators: {
                    notEmpty: {
                        message: 'El número de lavadas es requerido y no puede estar vacio'
                    },
                    numeric: {
                        message: 'El número de lavadas solo puede contener números'
                    },
                    greaterThan: {
                        value: 1,
                        message: 'El número de lavadas mínimo es 1'
                    }
                }
            },
            descripcion: {
                validators: {
                    notEmpty: {
                        message: 'La descripción es requerida y no puede estar vacia'
                    }
                }
            }
        }
    });
    $('#comboForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            comboHidden: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un Combo'
                    }
                }
            },
            modeloHidden: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un Modelo de Carro'
                    }
                }
            },
            precio: {
                validators: {
                    notEmpty: {
                        message: 'El precio es requerido y no puede estar vacio'
                    },
                    numeric: {
                        message: 'El precio debe ser númerico',
                        thousandsSeparator: '',
                        decimalSeparator: '.'
                    },
                    greaterThan: {
                        value: 10,
                        message: 'El precio mínimo es 10.00'
                    },
                    lessThan: {
                        value: 1000,
                        message: 'El precio máximo es 1000.00'
                    }
                }
            }
        }
    });
    $('#formSelClienteEvo').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            txtCliente: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un Cliente'
                    }
                }
            }
        }
    });
    $('#lavadaForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            placa: {
                validators: {
                    notEmpty: {
                        message: 'La placa es requerida y no puede estar vacia'
                    },
                    stringLength: {
                        max: 6,
                        min: 6,
                        message: 'La placa consta de 6 caracteres'
                    }
                }
            }
        }
    });
    console.log(1);
    $('#formObjetosCustodia').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nombre: {
                validators: {
                    notEmpty: {
                        message: 'El nombre del objeto es requerido y no puede estar vacio'
                    }
                }
            },
            cantidad: {
                validators: {
                    notEmpty: {
                        message: 'La cantidad del objeto es requerida y no puede estar vacia'
                    },
                    numeric: {
                        message: 'La cantidad del objeto debe ser númerico',
                        thousandsSeparator: '',
                        decimalSeparator: '.'
                    },
                    greaterThan: {
                        value: 1,
                        message: 'La cantidad del objeto mínima es 1'
                    },
                    lessThan: {
                        value: 1000,
                        message: 'La cantidad del objeto máxima es 1000'
                    }
                }
            }
        }
    });
    $('#recargaForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            idCliente: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un cliente de la lista'
                    }
                }
            },
            idCombo: {
                validators: {
                    notEmpty: {
                        message: 'Seleccione un combo de la lista'
                    }
                }
            }
        }
    });
});
